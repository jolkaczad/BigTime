/**
 * TEMPLATE
 */

I. PROJECT GOALS
----------------

    Create a tabbed (or otherwise separated into at least 3 planes) interface.
    - One plane for the map with points displayed
    - One plane with points listed in tabular form divided into categories
      imported from XML
    - One tab for configuration: button for reading from XML, statistics of
      points displayed

    Create at least three kinds of markers:
    - hot point
    - restaurant
    - sight

II. PROBLEMS ENCOUNTERED
------------------------

    1. Had problems with printing out a string through the LOG. Turns out
    you cannot send multiple newline ('\n') characters through the LOG. The
    printing will terminate at the point where these multiple newlines occur.

    2.
    Problem: if extracting a string from XML, the newlines inside the string
    will be also copied after parsing.

    Solution: Didn't find a good solution yet except for keeping the newlines
    in order inside the XML.

    3.
    Problem: Application was crashing when I tried to access resources from the fragment constructor

    Solution: I moved the "getActivity().getResources();" to the fragment's onCreateView method.
    It's possible the resources aren't ready yet when the constructor is called or the res. cannot
    be accessed when the fragment is not in view of the activity. The reason is unclear.

    4.


III. LESSONS LEARNED
--------------------

    1.
    Context object can only be returned from an Activity class

    2.
    Cannot use the same iterator on an ArrayList to add/delete elements and to iterate through
    the data structure. It will cause an exception. Creating a new iterator after all elements
    are in place is required.

IV. IDEAS
---------

    1.
    Can create a different marker bitmap for each type of marker like restaurant, club, park etc.