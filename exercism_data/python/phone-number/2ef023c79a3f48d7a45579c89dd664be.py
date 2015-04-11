"""A phone number."""


class PhoneNumber(object):
    """ A phone number."""

    BAD_NUMBER = "0000000000"

    def __init__(self, entered):
        self.number = entered

    def __repr__(self):
        return "{!s}({!r})".format(self.__class__.__name__, self.number)

    def __str__(self):
        return "({}) {}-{}".format(
            self.area_code(), self.exchange_code(), self.subscriber_number())

    @property
    def number(self):
        """Get the phone number."""
        return self._number

    @number.setter
    def number(self, value):
        """Set the phone number."""
        digits = filter(lambda c: c.isdigit(), value)
        ndigits = len(digits)
        if ndigits == 10:
            self._number = digits
        elif ndigits == 11 and digits[0] == "1":
            self._number = digits[1:]
        else:
            self._number = PhoneNumber.BAD_NUMBER

    def area_code(self):
        """Return the area code."""
        return self.number[:3]

    def exchange_code(self):
        """Return the exchange code."""
        return self.number[3:6]

    def subscriber_number(self):
        """Return the subscriber number."""
        return self.number[6:]

    def pretty(self):
        "Return the pretty-formatted phone number."
        return str(self)


# ``class Phone`` is an alias of ``class PhoneNumber``.
Phone = PhoneNumber
