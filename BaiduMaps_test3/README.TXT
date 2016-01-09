I. PROJECT GOALS
----------------

    Show present location on map. Center map on that location.

    Import points from an external XML file. XML fields: LN/LAT, name, description less than 140
    characters.

II. PROBLEMS ENCOUNTERED
------------------------

III. LESSONS LEARNED
--------------------

    1.
    Multiple exceptions can be caught with one branch like this:
        } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
        }
    2.
    Parsing the XML file was tricky at first but it's described quite well in
    http://www.xmlpull.org/v1/download/unpacked/doc/quick_intro.html

    3.
    The qualifier of the main activity class was deleted by accident and the application would crash
    immediately. Adding the keyword "public" in front fixed the issue. If the qualifier is not specified,
    the default behavior must be "private".