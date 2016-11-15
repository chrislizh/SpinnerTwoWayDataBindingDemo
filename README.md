# SpinnerTwoWayDataBindingDemo
This app is to demonstrate how to create two-way data binding for Android spinner utilizing bindingAdapter and InverseBindingAdapter mechanism.

The bound data can be any custom object (not just a String object) as long as the overridden toString() returns the desired text for the spinner.

As the spinner selection is bound to an ObservableField object, when a spinner item is selected, the ObservableField value changes accordingly.

On the other hand, when set a new value to the ObservableField, an item in the spinner will be automatically selected as well.

For better demonstrating the logic, this app uses an edit text view to show/change the value of the above mentioned ObservableField object.

Additionally, this app also demonstrates how to initialize the spinner selected item and takes care of configuration change.
