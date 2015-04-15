"""Number series of a given length in a string of digits."""


class Series(object):
    """A series of numbers."""

    def __init__(self, digits):
        """Create a series of numbers that corresponds to a string of digits.

        :param digits: a string of digits
        :type digits: str
        """
        self.numbers = SlidingWindowList(int(digit) for digit in digits)

    def __repr__(self):
        cls = self.__class__
        return "{!s}.{!s}({!r})".format(
            cls.__module__, cls.__name__,
            "".join(str(number) for number in self.numbers))

    def slices(self, size):
        """Return a list of slices (step=1) of a given size."""
        try:
            return list(self.numbers.sliding_window(size))
        except WindowSizeError as err:
            raise ValueError(
                "Invalid slice length for this series: {}".format(err))


class WindowSizeError(Exception):
    """Raised when a window size is invalid."""
    pass


class SlidingWindowList(list):
    """``list`` extended to support a sliding window."""

    def sliding_window(self, size):
        """Return a sliding window.

        :param size: the window size
        :rtype: iterator
        :raise WindowSizeError: if 1) the window size is less than zero, 2) the
                                window size is equal to zero and the list is
                                non-empty, or 3) the window size is greater
                                than the length of the list
        """
        if size < 0 or (size == 0 and self) or size > len(self):
            raise WindowSizeError(size)
        return (
            self[start:start + size] for start in xrange(len(self) - size + 1)
        )
