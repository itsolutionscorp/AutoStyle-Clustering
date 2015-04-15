class Phone(object):

    """A phone number"""

    LENGTH_OF_VALID_NUMBER = 10
    LENGTH_OF_AREA_CODE = 3
    LENGTH_OF_LAST_PART = 4
    COUNTRY_CODE = '1'
    INVALID_DIGIT = '0'

    def __init__(self, number):
        """Create a new phone number from given string."""
        self.number = self.cleanup(number)

    def cleanup(self, number):
        """Return cleaned up number."""
        clean_number = "".join([char for char in number if char.isdigit()])
        if len(clean_number) == self.LENGTH_OF_VALID_NUMBER:
            return clean_number
        elif len(clean_number) == self.LENGTH_OF_VALID_NUMBER + 1 and \
                clean_number.startswith(self.COUNTRY_CODE):
            return clean_number[1:]
        else:
            return self.LENGTH_OF_VALID_NUMBER * self.INVALID_DIGIT

    def area_code(self):
        """Return area code of phone number."""
        return self.number[:self.LENGTH_OF_AREA_CODE]

    def middle_part(self):
        """Return middle part of phone number."""
        return self.number[self.LENGTH_OF_AREA_CODE:
                           self.LENGTH_OF_VALID_NUMBER -
                           self.LENGTH_OF_LAST_PART]

    def last_part(self):
        """Return last part of phone number."""
        return self.number[self.LENGTH_OF_VALID_NUMBER -
                           self.LENGTH_OF_LAST_PART:]

    def pretty(self):
        """Return formatted phone number."""
        return "({0}) {1}-{2}".format(self.area_code(),
                                      self.middle_part(),
                                      self.last_part())
