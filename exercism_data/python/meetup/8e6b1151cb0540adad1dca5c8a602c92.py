from datetime import date


def meetup_day(y, m, d, ind):
    nums = ["1st", "2nd", "3rd", "4th", "5th"]
    days = ["Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"]

    first_day_of_month = date(y, m, 1)
    start_day_of_week = first_day_of_month.weekday()

    # offset gives how many days between start of the month and desired day
    offset = (days.index(d) - start_day_of_week) % 7

    if ind in nums:
        try:
            return date(y, m, 1 + offset + 7 * nums.index(ind))
        except:
            raise ValueError("Desired date does not exist in the month")

    if ind == "last":
        for i in reversed(xrange(0, 5)):
            try:
                return date(y, m, 1 + offset + 7 * i)
            except ValueError:
                pass

    if ind == "teenth":
        for i in xrange(1, 4):
            day_date = 1 + offset + 7 * i
            if day_date > 12 and day_date < 20:
                return date(y, m, day_date)
