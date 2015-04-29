class Meetup
  attr_reader :days_of_month

  ORDER = [:first, :second, :third, :fourth]

  def initialize(m, y)
    @m = m
    @y = y
    d = Date.new(y, m, 1)
    @days_of_month = (d...d.next_month)
  end

  def day(day, schedule)
    days = days_of_month.select(&"#{day}?".to_sym)
    if ORDER.include?(schedule)
      days[ORDER.index(schedule)]
    elsif schedule == :teenth
      days.detect { |d| d.day.between?(13, 19) }
    elsif schedule == :last
      days.last
    end
  end

end
