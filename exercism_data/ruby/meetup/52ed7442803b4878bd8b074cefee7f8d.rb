class Meetup
  @@WEEKDAY_MAP = {
    sunday:     0,
    monday:     1,
    tuesday:    2,
    wednesday:  3,
    thursday:   4,
    friday:     5,
    saturday:   6
  }
  @@ROUND = {
    first:  0,
    second: 1,
    third:  2,
    fourth: 3
  }

  def initialize mon, year
    @mon, @year = mon, year
  end

  def day weekday, special
    # first day of the month
    date = Date.new(@year, @mon, 1)

    date += 1 until date.wday == @@WEEKDAY_MAP[weekday]
    case special
    when :teenth
      date += 7 until date.day.between?(13, 19)
    when :last
      # last day of the month
      date = Date.new(*@year, @mon, -1)
      date -= 1 until date.wday == @@WEEKDAY_MAP[weekday]
    else
      date += 7 * @@ROUND[special]
    end
    date
  end
end
