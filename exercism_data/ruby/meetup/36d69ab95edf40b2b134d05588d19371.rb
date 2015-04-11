# This screams for some metaprogramming!
class Meetup
  attr_accessor :month

  def initialize(month, year)
    self.month = Date.new year, month, 1
  end

  def self.make_day_of_week_method(name, week_name, day_of_week)
    define_method name do
      week = send "#{week_name}_week"
      week_day week, day_of_week
    end
  end

  Date::DAYNAMES.map {|day| day.downcase}.each_with_index do |day_name, day_of_week|
    make_day_of_week_method day_name.gsub('day', 'teenth'), 'teenth', day_of_week
    make_day_of_week_method "last_#{day_name}", "last", day_of_week

    %w(first second third fourth).each_with_index do |week_name, week_number|
      define_method "#{week_name}_week" do
        nth_week week_number
      end

      make_day_of_week_method "#{week_name}_#{day_name}", week_name, day_of_week
    end
  end

  private

  alias :start_of_month :month

  def end_of_month
    month.next_month.prev_day
  end

  def last_week
    weekdays end_of_month - 6
  end

  def nth_week(week)
    weekdays start_of_month + week * 7
  end

  def teenth_week
    weekdays month + 12
  end

  def weekdays(start_day)
    (start_day..start_day + 6).to_a
  end

  def week_day(week, day_of_week)
    week[(day_of_week + week_offset(week)) % 7]
  end

  def week_offset(week)
    - week[0].wday
  end
end
