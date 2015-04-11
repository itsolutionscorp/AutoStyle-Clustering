#!/usr/bin/python

class Beer(object):
    def __init__(self):
        self.max_num_bottles = 99
        self.curr_count = 0
        self.verse_tmpl = "{0:} on the wall, {1:}.\n{2:}, {3:} on the wall.\n"

    def is_singular_noun(self):
        return self.curr_count == 1

    def is_count_zero(self):
        return self.curr_count == 0

    def count(self):
        if self.is_singular_noun():
            return "{0:} bottle of beer".format(self.curr_count)
        elif self.is_count_zero():
            return "No more bottles of beer"
        return "{0:} bottles of beer".format(self.curr_count)

    def act(self):
        if self.curr_count == 0:
            self.curr_count = self.max_num_bottles
            return "Go to the store and buy some more"
        elif self.curr_count == 1:
            self.curr_count = 0
            return "Take it down and pass it around"
        else:
            self.curr_count = self.curr_count - 1
            return "Take one down and pass it around"

    def say(self, pre=None, action=None, post=None):
        return self.verse_tmpl.format(pre, pre.lower(), action, post)

    def verse(self, num_bottles):
        self.curr_count = num_bottles

        pre = self.count()
        action = self.act()
        post = self.count()

        return self.say(pre, action, post.lower())

    def sing(self, end, start=0):
        result = ""
        for num in xrange(end, start-1, -1):
            result += self.verse(num)
            result += "\n"
        return result
