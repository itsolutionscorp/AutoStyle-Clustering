class Meetup
  WEEKDAYS = %w[monday tuesday wednesday thursday friday saturday sunday]
  ORDINALS = %w[first second third fourth]

  def initialize(month, year)
    @month = month
    @year = year
  end

  WEEKDAYS.each do |weekday|
    prefix = weekday.sub("day", "")              # "mon"

    define_method("#{prefix}teenth") do          # def monteenth
      find_teen weekdays(weekday)                #   teens weekdays("monday")
    end                                          # end

    define_method("last_#{weekday}") do          # def last_monday
      weekdays(weekday).last                     #   weekdays("monday").last
    end                                          # end

    ORDINALS.each_with_index do |ordinal, i|
      define_method("#{ordinal}_#{weekday}") do  # def first_monday
        weekdays(weekday)[i]                     #   weekdays("monday")[0]
      end                                        # end
    end
  end

  private

  def find_teen(dates)
    dates.find { |date| date.day.between?(13, 19) }
  end

  def weekdays(weekday)
    days_of_month.select(&:"#{weekday}?")
  end

  def days_of_month
    Date.new(@year, @month, 1)..Date.new(@year, @month, -1)
  end
end
