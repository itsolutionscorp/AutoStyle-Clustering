class Meetup
  DAYS = {
    teenth: (13..19),
    first: (1..7),
    second: (8..14),
    third: (15..21),
    fourth: (22..28),
    last: (-7..-1)
  }

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    dates = DAYS[schedule].map { |day| Date.new(@year, @month, day) }
    dates.find { |date| date.send("#{weekday}?") }
  end
end
