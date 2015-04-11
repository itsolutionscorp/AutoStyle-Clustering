class Meetup

  TEENTH_RANGE      = 13..19
  MEETUP_TYPES      = [:first, :second, :third, :fourth]
  DAYS_OF_THE_WEEK  = %w(monday tuesday wednesday thursday friday saturday sunday)

  DAYS_OF_THE_WEEK.each do |day|
    define_method(day.to_sym) do 
      dates_objects_in_month.map do |m| 
        m if DAYS_OF_THE_WEEK[m.wday-1] == day
      end
    end
  end

  MEETUP_TYPES.each do |meetup|
    define_method(meetup) do |weekdays|
      weekdays.compact[MEETUP_TYPES.index(meetup)]
    end
  end

  attr_reader :month, :year, :number_of_days_in_month, :dates_objects_in_month

  def initialize(month, year)
    @month = month
    @year  = year
    @number_of_days_in_month = Date.new(year, month, -1).mday
    @dates_objects_in_month = month_builder
  end

  def day(weekday, meetup)
    self.send(meetup, self.send(weekday))
  end

  def month_builder
    (1..number_of_days_in_month).map { |d| Date.new(year, month, d) }
  end

  def teenth(weekdays)
    weekdays.detect.with_index { |d,i| d && TEENTH_RANGE.include?(i+1) }
  end

  def last(weekdays)
    weekdays.reverse.detect { |d| d }
  end

end
