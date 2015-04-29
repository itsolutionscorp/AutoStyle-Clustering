class Series(object):

    """Series representation."""

    def __init__(self, digits):
        """constructs a new series with digits.

        :digits: @todo

        """
        self._digits = digits

    def slices(self, length):
        """slices the digit sequence into slices of size 'length'

        :length: @todo
        :returns: @todo

        """
        self._validate_length(length)
        slices = []
        digit_amount = len(self._digits)
        for i in xrange(0, digit_amount-length+1):
            slices.append([int(n) for n in list(self._digits[i:i+length])])

        return slices

    def _validate_length(self, length):
        """checks wether length is in range

        :length: the length to check
        """
        if length == 0 or length > len(self._digits):
            raise ValueError("Invalid slice length for this series: %d" % (length))
