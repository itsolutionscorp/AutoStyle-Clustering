class Meetup
  OFFSETS = { first: 1, second: 8, third: 15, fourth: 22, last: -7, teenth: 13 }

  def initialize month, year
    @month, @year = month, year
  end

  def day weekday, schedule
    d = Date.new(@year, @month, OFFSETS[schedule])
    d += 1 until Date::DAYNAMES[d.wday].downcase == weekday.to_s
    d
  end
end
