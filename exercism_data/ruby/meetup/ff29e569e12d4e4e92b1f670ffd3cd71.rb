class Meetup
  WEEKDAYS = %w[monday tuesday wednesday thursday friday saturday sunday]
  ORDINALS = %w[first second third fourth]

  def initialize(month, year)
    @month = month
    @year = year
  end

  WEEKDAYS.each do |weekday|
    prefix = weekday.sub("day", "")      # "mon"

    define_method("#{prefix}teenth") do  # def monteenth
      teenth(weekday)                    #   teenth("monday")
    end                                  # end

    ORDINALS.each_with_index do |ordinal, i|
      define_method("#{ordinal}_#{weekday}") do  # def first_monday
        nth(weekday, i)                          #   nth("monday", i)
      end                                        # end
    end

    define_method("last_#{weekday}") do  # def last_monday
      last(weekday)                      #   last("monday")
    end                                  # end
  end

  private

  def teenth(weekday)
    days_of_month.find { |date| weekday?(date, weekday) && teen?(date) }
  end

  def nth(weekday, i)
    days_of_month.select { |date| weekday?(date, weekday) }.slice(i)
  end

  def last(weekday)
    days_of_month.reverse.find { |date| weekday?(date, weekday) }
  end

  def days_of_month
    (first_day_of_month..last_day_of_month).to_a
  end

  def first_day_of_month
    Date.new(@year, @month, 1)
  end

  def last_day_of_month
    (first_day_of_month >> 1) - 1
  end

  def weekday?(date, weekday)
    date.public_send("#{weekday}?")
  end

  def teen?(date)
    (13..19).include?(date.day)
  end
end
