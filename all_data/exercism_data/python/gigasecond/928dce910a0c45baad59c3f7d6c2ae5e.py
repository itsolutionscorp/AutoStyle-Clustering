"""One gigasecond later."""

__all__ = ["Gigasecond"]

from datetime import timedelta


class Gigasecond(object):
    """One gigasecond later."""

    ONE_GIGASECOND = timedelta(seconds=10 ** 9)

    def __init__(self, date):
        """Create a date that is 1 gigasecond later from a given date.

        :param date: a start date.
        :type date: datetime.date.
        """
        self.date = date + Gigasecond.ONE_GIGASECOND

    def __repr__(self):
        cls = self.__class__
        return "<{!s}.{!s} {!s} date={!r}>".format(
            cls.__module__, cls.__name__, hex(id(self)), self.date)

    def __str__(self):
        return str(self.date)
