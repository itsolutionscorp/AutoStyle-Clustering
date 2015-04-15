class Series:

    def __init__(self, values):
        self.values = [int(v) for v in values]

    def slices(self, slice):
        if slice == 0 or slice > len(self.values):
            raise ValueError('Invalid slice length for this series: ' +
                             str(slice))

        else:
            ret_list = []
            i = 0
            while i+slice-1 < len(self.values):
                ret_list.append(self.values[i:i+slice])
                i += 1
            return ret_list
