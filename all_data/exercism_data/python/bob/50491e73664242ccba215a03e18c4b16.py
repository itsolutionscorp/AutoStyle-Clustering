import sys, re, unicodedata, operator

# python does not have \p-style unicode regex, see
# http://stackoverflow.com/questions/1832893/python-regex-matching-unicode-properties/1836283
class Bob:
    _lower=reduce(\
        operator.add,
        filter(lambda u: unicodedata.category(u)=='Ll',\
                      map(unichr, range(sys.maxunicode))))

    _responses=[\
        (re.compile(r"^\s*$"),'Fine. Be that way!'),\
            (re.compile(r"^[^"+_lower+"]*[A-Z][^"+_lower+"]*$"),\
             'Woah, chill out!'),\
            (re.compile(r".*\?$"),"Sure."),\
            (re.compile(r".*"), "Whatever.") ]
    def hey(self, query):
        return filter(lambda x: x[0].match(query), self._responses)[0][1]
