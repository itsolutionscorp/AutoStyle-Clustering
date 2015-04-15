class Meetup
  def self.days_of_week
    [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]
  end

  def self.weekday_number(weekday)
    days_of_week.index(weekday)
  end

  attr_reader :year, :month
  def initialize(month, year)
    @year = year
    @number = month
    @first = Date.new(year, month, 1)
    @eighth = Date.new(year, month, 8)
    @thirteenth = Date.new(year, month, 13)
    @fifteenth = Date.new(year, month, 15)
    @twenty_second = Date.new(year, month, 22)
    @last = Date.new(year, month, -1)
  end

  def day(weekday, schedule)
    case schedule
    when :teenth
      @thirteenth + days_til(weekday, @thirteenth)
    when :first
      @first + days_til(weekday, @first)
    when :second
      @eighth + days_til(weekday, @eighth)
    when :third
      @fifteenth + days_til(weekday, @fifteenth)
    when :fourth
      @twenty_second + days_til(weekday, @twenty_second)
    when :last
      @last - (7 - (self.class.weekday_number(weekday) - @last.wday)) % 7
    end
  end

  private

  def days_til(weekday, day)
    (self.class.weekday_number(weekday) - day.wday) % 7
  end
end
