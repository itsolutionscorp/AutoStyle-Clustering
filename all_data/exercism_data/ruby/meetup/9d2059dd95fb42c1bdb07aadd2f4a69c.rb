# This screams for some metaprogramming!
class Meetup

  class << self
    private

    def self.make_day_of_week_method(name, week_name, day_of_week)
      # Does this for Friday, day 5 of the week
      # def third_friday
      #   week = third_week
      #   find_week_day week, 5
      # end
      #
      define_method name do
        week = send "#{week_name}_week"
        find_week_day week, day_of_week
      end
    end
  end

  Date::DAYNAMES.map {|day| day.downcase}.each_with_index do |day_name, day_of_week|
    make_day_of_week_method day_name.gsub('day', 'teenth'), 'teenth', day_of_week
    make_day_of_week_method "last_#{day_name}", "last", day_of_week

    %w(first second third fourth).each_with_index do |week_name, week_number|
      week_name_method = "#{week_name}_week"
      define_method week_name_method do
        nth_week week_number
      end
      private week_name_method

      make_day_of_week_method "#{week_name}_#{day_name}", week_name, day_of_week
    end
  end

  attr_accessor :month

  def initialize(month, year)
    self.month = Date.new year, month, 1
  end

  private

  alias :start_of_month :month

  def end_of_month
    month.next_month.prev_day
  end

  def find_week_day(week, day_of_week)
    location_of_day_in_week = (day_of_week + week_offset_for(week)) % 7
    week[location_of_day_in_week]
  end

  def last_week
    last_week_starts_on_day = end_of_month - 6
    week_of_days_starting_on last_week_starts_on_day
  end

  def nth_week(week_number)
    number_of_days = week_number * 7
    week_of_days_starting_on start_of_month + number_of_days
  end

  def teenth_week
    teenth_week_starts_on_day = start_of_month + 12
    week_of_days_starting_on teenth_week_starts_on_day
  end

  def week_of_days_starting_on(start_day)
    last_day_of_week = start_day + 6
    (start_day..last_day_of_week).to_a
  end

  def week_offset_for(week)
    - week[0].wday
  end
end

require 'debugger'; debugger
