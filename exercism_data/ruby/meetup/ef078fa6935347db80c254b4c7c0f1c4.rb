class Meetup
  WEEKDAYS = Date::DAYNAMES.map(&:downcase)
  ORDINALS = %w[first second third fourth]

  def initialize(month_number, year)
    @month = Month.new(month_number, year)
  end

  WEEKDAYS.each do |weekday|
    prefix = weekday.sub("day", "")              # "mon"

    define_method("#{prefix}teenth") do          # def monteenth
      weekdays(weekday).teenth                   #   weekdays("monday").teenth
    end                                          # end

    ORDINALS.each_with_index do |ordinal, i|
      define_method("#{ordinal}_#{weekday}") do  # def first_monday
        weekdays(weekday)[i]                     #   weekdays("monday")[0]
      end                                        # end
    end

    define_method("last_#{weekday}") do          # def last_monday
      weekdays(weekday).last                     #   weekdays("monday").last
    end                                          # end
  end

  private

  def weekdays(weekday)
    WeekdaysOfMonth.new(weekday, @month)
  end
end


class WeekdaysOfMonth
  def initialize(weekday, month)
    @weekday = weekday
    @month = month
  end

  def teenth
    days.find { |date| (13..19).include?(date.day) }
  end

  def last
    days.last
  end

  def [](index)
    days[index]
  end

  private

  def days
    @month.days.select(&:"#{@weekday}?")
  end
end


class Month
  def initialize(number, year)
    @number = number
    @year = year
  end

  def days
    first_day..last_day
  end

  private

  def first_day
    Date.new(@year, @number, 1)
  end

  def last_day
    # Advance one month, then back one day.
    (first_day >> 1) - 1
  end
end
