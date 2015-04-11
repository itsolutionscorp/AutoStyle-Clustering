class Beer:

    def verse(self, idx):
        s = '%s, %s.\n' \
                % (self._get_wall_string(idx), self._get_beer_string(idx).lower())
        if idx > 0:
            it_str = 'it' if idx == 1 else 'one'
            s = s + 'Take %s down and pass it around, ' % it_str
            s = s + '%s.\n' % self._get_wall_string(idx - 1).lower()
        else:
            s = s + 'Go to the store and buy some more, %s.\n' \
                    % self._get_wall_string(99)
        return s

    def _get_beer_string(self, idx):
        beer_cnt = str(idx) if idx > 0 else 'No more'
        bottle_str = 'bottle' if idx == 1 else 'bottles'
        return '%s %s of beer' % (beer_cnt, bottle_str)

    def _get_wall_string(self, idx):
        return '%s on the wall' % self._get_beer_string(idx)

    def sing(self, start_idx, end_idx = 0):
        s = ''
        for i in reversed(xrange(end_idx, start_idx + 1)):
            s = s + self.verse(i) + '\n'
        return s
