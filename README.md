# SpinnerTwoWayDataBindingDemo
This app is to demonstrate how to create two-way data binding for Android spinner utilizing bindingAdapter and InverseBindingAdapter mechanism.

The bound data can be any custom object (not just a String object) as long as the overridden toString() returns the desired text for the spinner.

As the spinner selection is bound to an observable, when a spinner item is selected, the observable value changes accordingly.

On the other hand, when set a new value in the observable, an item in the spinner will be automatically selected as well.

For better demonstrating the logic, this app uses an edit text view to show/change the value of the above mentioned observable.

Additionally, this app also demonstrates how to initialize the spinner selected item and takes care of configuration change.
