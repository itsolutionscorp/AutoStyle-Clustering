class Beer(object):
    def __init__(self):
        self.verse_string_1 = "{cur_num} bottle{cur_pl} of beer on the wall, {cur_num} bottle{cur_pl} of beer.\n"
        self.verse_string_2 = "Take {how_many} down and pass it around, {next_num} bottle{next_pl} of beer on the wall.\n"
        self.verse_string_0 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

    def _verse_string(self, i):
        if i > 0:
            return self.verse_string_1+self.verse_string_2
        return self.verse_string_1+self.verse_string_0

    def _next_verse(self, i):
        return i - 1

    def _plural(self, i):
        if i != 1:
            return 's'
        return ''

    def _capitalize(self, verse):
        return verse[0].upper()+verse[1:]

    def verse(self, num_bottles):
        kwargs = {
                'cur_num': num_bottles,
                'cur_pl': self._plural(num_bottles),
                'how_many': 'one',
                'next_num': self._next_verse(num_bottles),
                'next_pl': self._plural(self._next_verse(num_bottles))
            }
        if num_bottles == 1:
            kwargs['how_many'] = 'it'
            kwargs['next_num'] = 'no more'
        if num_bottles == 0:
            kwargs['cur_num'] = 'no more'
        return self._capitalize(self._verse_string(num_bottles).format(**kwargs))

    def sing(self, start_verse, end_verse=None):
        if end_verse is None:
            end_verse = 0
        verses = reversed([self.verse(i) for i in range(end_verse, start_verse+1)])
        return "\n".join(verses)+"\n"
