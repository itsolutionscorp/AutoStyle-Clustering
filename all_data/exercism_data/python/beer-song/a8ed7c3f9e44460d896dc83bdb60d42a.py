verse_tmpl = """\
%s of beer on the wall, %s of beer.
Take one down and pass it around, %s of beer on the wall.
"""

empty_tmpl = """\
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
"""

class Beer(object):

    def bottles(self, bottle_num):
        bottle_info = {
            1:  "1 bottle",
            0:  "no more bottles"
        }
        return bottle_info.get(bottle_num, "%s bottles" % (bottle_num))

    def verse(self, verse_num):
        if verse_num == 0:
            return empty_tmpl

        cur_bottles = self.bottles(verse_num)
        next_bottles = self.bottles(verse_num-1)

        result = verse_tmpl % (cur_bottles, cur_bottles, next_bottles)

        if verse_num == 1:
            result = result.replace("one", "it")

        return result

    def sing(self, verse_start, verse_stop=0):
        return "\n".join(self.verse(i) for i in range(verse_start, verse_stop-1, -1)) + "\n"

"""
Write a program which produces the lyrics to that beloved classic, that field-trip favorite: 99 Bottles of Beer on the Wall.

Note that not all verses are identical.

The verse for 1 bottle is as follows:

```plain
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
```

The verse for 0 bottles is as follows:

```plain
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
```
"""
