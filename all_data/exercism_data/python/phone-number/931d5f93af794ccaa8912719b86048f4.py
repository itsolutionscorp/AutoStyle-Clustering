class Phone(object):
    sections = ['area_code', 'exchange', 'line']
    pretty_format = '({area_code}) {exchange}-{line}'

    def __init__(self, user_number):
        clean_number = raw_number = filter(lambda c: c.isdigit(), user_number)

        if len(raw_number) == 11 and raw_number[0] == '1':
            clean_number = raw_number[1:]
        elif len(raw_number) != 10:
            clean_number = '0000000000'  # TODO: better to raise error here?

        self.number = clean_number

    def area_code(self):
        """Get area code (first three digits)"""
        return self.number[:3]

    def exchange(self):
        """Get exchange (first three digits after area code)"""
        return self.number[3:6]

    def line(self):
        """Get line number (last four digits)"""
        return self.number[6:]

    def pretty(self):
        """Output number in pretty format: (XXX) XXX-XXXX"""
        format_kwargs = {}

        for section in self.sections:
            format_kwargs[section] = getattr(self, section)()

        return self.pretty_format.format(**format_kwargs)
