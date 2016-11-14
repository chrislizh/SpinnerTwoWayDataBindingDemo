package au.com.chrisli.spinnertwowaydatabindingdemo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import au.com.chrisli.spinnertwowaydatabindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IDataChangeListener {

    private static final String BUNDLE_SELECTED_PLANET = "bundle_selected_planet";
    private ActivityMainBinding activityMainBinding_;
    private List<Planet> planets_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding_ = DataBindingUtil.setContentView(this, R.layout.activity_main);

        planets_ = loadPlanets(this, R.array.planetsInSolarSystem);
        if (planets_ != null) {
            planets_.add(0, new Planet("", 0)); //insert a blank item on the top of the list
            activityMainBinding_.setSpinAdapterPlanet(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planets_));
            Planet selectedPlanet = savedInstanceState != null ? savedInstanceState.<Planet> getParcelable(BUNDLE_SELECTED_PLANET)
                    : planets_.get(3);//initial selected planet is Earth, 3 is the index of Earth after a blank item inserted
            activityMainBinding_.setBindingPlanet(new BindingPlanet(this, selectedPlanet));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_SELECTED_PLANET, activityMainBinding_.getBindingPlanet().obvSelectedPlanet_.get());
    }

    @Override
    public void onEditTextChanged(String planetName) {
        if (planetName != null && planets_ != null) {
            Planet targetPlanet = planets_.get(0); //set the blank item as the target first until the text input by users has a match
            for (Planet planet : planets_) {
                if (planet != null) {
                    if (planetName.equalsIgnoreCase(planet.getName())) {
                        targetPlanet = planet;
                        break;
                    }
                }
            }
            activityMainBinding_.getBindingPlanet().obvSelectedPlanet_.set(targetPlanet);
        }
    }

    private List<Planet> loadPlanets(Context context, int resourceId) {
        List<Planet> planets = null;

        if (context != null) {
            TypedArray typedArrayPlanets = null;
            try {
                typedArrayPlanets = getResources().obtainTypedArray(resourceId);
                for (int i = 0; i < typedArrayPlanets.length(); i++) {
                    Planet planet = loadPlanet(context, typedArrayPlanets.getResourceId(i, 0));
                    if (planet != null) {
                        if (planets == null) { //lazy instantiation
                            planets = new ArrayList<>();
                        }
                        planets.add(planet);
                    }
                }
                typedArrayPlanets.recycle();
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        return planets;
    }

    private Planet loadPlanet(Context context, int resourceId) {
        Planet planet = null;

        if (context != null) {
            TypedArray typedArrayPlanet = null;
            try {
                String name = null;
                float distance = 0;
                typedArrayPlanet = context.getResources().obtainTypedArray(resourceId);
                for (int i = 0; i < typedArrayPlanet.length(); i++) {
                    if (i == 0) {
                        name = typedArrayPlanet.getString(i);
                    } else if (i == 1) {
                        distance = Float.valueOf(typedArrayPlanet.getString(i));
                    }
                }
                if (name != null && distance > 0) {
                    planet = new Planet(name, distance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (typedArrayPlanet != null) {
                    typedArrayPlanet.recycle();
                }
            }
        }

        return planet;
    }
}
