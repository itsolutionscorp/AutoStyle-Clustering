class Meetup
  require 'date'

  SCHEDULE_OPTIONS = {
    :teenth => 13..19,
    :first => 1..7,
    :second => 8..14,
    :third => 15..21,
    :fourth => 22..28,
  }

  def initialize(month, year)
    @month = month
    @year = year
    last_day_of_month = Date.civil(@year,@month,-1).day
    SCHEDULE_OPTIONS[:last] = last_day_of_month - 6..last_day_of_month
  end

  def day(weekday, schedule)
    weekday_in_range(weekday, SCHEDULE_OPTIONS[schedule])
  end

  private

  def weekday_in_range(weekday, range)
    range.each do |day|
      date = Date.new(@year,@month,day)
      return date if date.dayname == weekday.to_s.capitalize
    end
  end

end

class Date
  def dayname
    DAYNAMES[self.wday]
  end
end
