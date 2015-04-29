# -*- coding:utf-8 -*-
class Beer(object):

    _default_template = (
        "%(current)s bottles of beer on the wall, %(current)s bottles of beer.\n"
        "Take one down and pass it around, %(next)s bottles of beer on the wall.\n"
        )

    _special_templates = {
        # verse_num : template
        0: (
            "No more bottles of beer on the wall, no more bottles of beer.\n"
            "Go to the store and buy some more, %(max)s bottles of beer on the wall.\n"
            ),
        1: (
            "%(current)s bottle of beer on the wall, %(current)s bottle of beer.\n"
            "Take it down and pass it around, no more bottles of beer on the wall.\n"
            ),
        2: (
            "%(current)s bottles of beer on the wall, %(current)s bottles of beer.\n"
            "Take one down and pass it around, %(next)s bottle of beer on the wall.\n"
            ),
        }

    _max = 99

    def verse(self, verse_number):
        template = self._special_templates.get(verse_number, self._default_template)
        context = dict(current=verse_number, next=verse_number-1, max=self._max)
        return template % context

    def sing(self, first, last=0):
        return "\n".join(reversed([self.verse(x) for x in xrange(last, first+1)])) + "\n"
                         
