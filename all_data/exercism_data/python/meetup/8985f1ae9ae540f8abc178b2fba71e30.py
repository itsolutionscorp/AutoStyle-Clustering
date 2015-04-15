import calendar
import sys
import re
from datetime import date


def meetup_day(year, month, weekday, nth):
    month = Month(year, month)
    return month.get_date(weekday, nth)


class Month:

    index = -1
    data = []
    max = 0
    year = None
    month = None

    def __init__(self, year, month):
        """
        :param int year:
        :param int month:
        :return: None
        """
        self.year, self.month = year, month
        self.data = self.__get_data(year, month)
        self.max = len(self.data)

    def __iter__(self):
        """
        :return: Month
        """
        return self

    def __next__(self):
        """
        :return: (int, int)
        """
        self.index += 1

        if self.index == self.max:
            raise StopIteration

        return self.data[self.index]

    def __getitem__(self, item):
        """
        :param index item:
        :return: (int, int)
        """
        return self.data[item - 1]

    def get_date(self, weekday, nth):
        """
        Get date in the current month.

        :param int weekday:
        :param str nth:
        :return: date
        """
        weekday = self.__weekday_to_num(weekday)
        day = self.__get_day(weekday, nth)
        return date(self.year, self.month, day)

    def __get_data(self, year, month):
        """
        Get data of the actual month.

        :param int year:
        :param int month:
        :return: [(int, int)]
        """
        first_weekday, num_days = calendar.monthrange(year, month)
        days_weekdays = calendar.Calendar().itermonthdays2(year, month)

        """
        itermonthdays2 always starts with monday and ends with sunday.
        That's why we have to crop the list to that entries, that actual belong to the current month
        """
        return list(days_weekdays)[first_weekday:first_weekday + num_days]

    def __get_day(self, weekday, nth):
        """
        Converts weekday-nth combination to the actual day.

        :param int weekday:
        :param str nth:
        :return: int
        """
        monthdays_filter = self.__get_filter(weekday, nth)
        filtered = monthdays_filter(self.data)

        monthdays_iter = MonthdaysIter(filtered)
        index = monthdays_iter.nth_to_index(nth)

        day, _ = monthdays_iter[index]
        return day

    def __get_filter(self, weekday, nth):
        """
        Get the filter function based on weekday-nth combination.

        :param int weekday:
        :param str nth:
        :return: callable
        """
        default = lambda data: \
            [item for item in data if item[1] == weekday]
        special = None

        if nth == 'first':
            special = lambda data: \
                [default(data)[0]]
        elif nth == 'last':
            special = lambda data: \
                [default(data)[-1]]
        elif nth == 'teenth':
            special = lambda data: \
                [item for item in data[12:20] if item[1] == weekday]

        return special if special else default

    def __weekday_to_num(self, str):
        """
        Convert a string representation of a weekday to its corresponding int

        :param str str: A string representation of a weekday, e.g. 'Monday'
        :return: int
        """
        return getattr(sys.modules['calendar'], str.upper())


class MonthdaysIter:

    index = -1
    data = []
    max = 0

    def __init__(self, data):
        """
        :param [(int, int)] data: the month data map
        :return: None
        """
        self.data = data
        self.max = len(self.data)

    def __iter__(self):
        """
        :return: MonthdaysIter
        """
        return self

    def __next__(self):
        """
        :return: (int, int)
        """
        self.index += 1

        if self.index == self.max:
            raise StopIteration

        return self.data[self.index]

    def __getitem__(self, item):
        """
        :param int item:
        :return: (int, int)
        """
        return self.data[item]

    def nth_to_index(self, nth):
        """
        Converts '1st', 'last', 'teenth' etc. to its corresponding index in a list

        :param str nth: e.g. '1st', 'last' or 'teenth
        :return: int
        """
        if nth == 'first' or nth == 'teenth':
            return 0
        elif nth == 'last':
            return -1

        return int(re.search('^\d+', nth).group(0)) - 1
