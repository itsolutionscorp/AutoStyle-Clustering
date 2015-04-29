class Meetup
  attr_reader :days_of_month

  DAYS = [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]
  ORDER = [:first, :second, :third, :fourth]

  def initialize(m, y)
    @m = m
    @y = y
    @days_of_month = (1..Date.new(y, m, -1).mday).map { |d| Date.new(y, m, d) }
  end

  def day(day, schedule)
    days = days_of_month.select { |d| d.wday == DAYS.index(day) }
    if ORDER.include?(schedule)
      days.compact[ORDER.index(schedule)]
    elsif schedule == :teenth
      days.detect { |d| d.mday.between?(13,19) }
    elsif schedule == :last
      days.reverse.first
    end
  end

end
