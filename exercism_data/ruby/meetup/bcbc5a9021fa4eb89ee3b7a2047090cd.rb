class Meetup

  def initialize(month, year)
    @month = month
    @year = year
  end

  WEEKDAYS = Date::DAYNAMES.map(&:downcase)
  WEEKS = %w(first second third fourth)

  WEEKDAYS.each do |weekday|

    # Define the 'monteenth' methods
    define_method(weekday.gsub('day', 'teenth')) {
      select_first { |date| date.send(weekday+'?') && date.teenth? }
    }

    # Define the 'first_monday' -> 'forth_saturday' methods
    WEEKS.each_with_index do |week, index|
      define_method("#{week}_#{weekday}") {
        select_first { |date| date.day > (index*7) && date.send(weekday+'?') }
      }
    end

    # Define the 'last_monday' methods
    define_method("last_#{weekday}") {
      select_last { |date| date.send(weekday+'?') }
    }

  end

  private

  def select_first(&block)
    select_day(1..days_in_month, &block)
  end

  def select_last(&block)
    select_day((1..days_in_month).to_a.reverse, &block)
  end

  def select_day(days, &block)
    days.each do |day|
      date = Date.new(@year, @month, day)
      return date if block.call(date)
    end
  end

  def days_in_month
    Date.new(@year, @month, -1).day
  end

end

class Date
  def teenth?
    (13..19).include?(self.day)
  end
end
