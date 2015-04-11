# You're using Python Standard Library?
import collections
# But that's cheating!!
import re

# Little classes are like snowflakes, there are no two alike.
Phrase = type("", (str, ), {"word_count": lambda self: collections.Counter(re.findall("\w+", self.lower()))})
