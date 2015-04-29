class Month
  def initialize month, year
    @month, @year = month, year
  end

  attr_reader :month, :year

  DAYS = %w(monday tuesday wednesday thursday friday saturday sunday)
  DAYS.each do |day|
    types = [:first, :second, :third, :fourth, :last]
    types.each do |type|
      define_method("#{type}_#{day}") do               # def first_monday
        find(type, day)                                #   find(:first, 'monday')
      end                                              # end
    end                                                #
                                                       #
    day_teenth = day.sub 'day', 'teenth'               #
    define_method(day_teenth) do                       # def monteenth
      find(:teenth, day)                               #   find(:teenth, 'monday')
    end                                                # end
  end

  def find(type, day)
    case type
    when :first  then weekdays(day)[0]
    when :second then weekdays(day)[1]
    when :third  then weekdays(day)[2]
    when :fourth then weekdays(day)[3]
    when :last   then weekdays(day).last
    when :teenth
      weekdays(day).find { |date| date.day.between?(13, 19)}
    end
  end

  def weekdays(day)
    days.select(&:"#{day}?")
  end

  def days
    Date.new(year, month, 1)..Date.new(year, month, -1)
  end
end

Meetup = Month
