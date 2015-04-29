class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    d = Date.new(@year, @month, OFFSETS[schedule])
    d + (7 + WEEKDAYS.index(weekday) - d.wday) % 7
  end

  private

  WEEKDAYS = [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]

  OFFSETS = Hash[:first, 1, :second, 8, :third, 15, :fourth, 22, :last, -7, :teenth, 13]
end
