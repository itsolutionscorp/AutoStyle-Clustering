require 'date'

class Meetup
  def initialize(month, year)
    @month = month
    @year = year

    map_day_hash
  end

  def day(week_day, schedule)
    day = case schedule
          when :first
            @day_hash[week_day].first
          when :second
            @day_hash[week_day][1]
          when :third
            @day_hash[week_day][2]
          when :fourth
            @day_hash[week_day][3]
          when :last
            @day_hash[week_day].last
          else
            ((13..19).to_a & @day_hash[week_day]).first
          end

    Date.new(@year, @month, day)
  end

  private

  def map_day_hash
    @day_hash = {}
    date = Date.new(@year, @month, 1)

    while date.month == @month
      (@day_hash[day_name(date.wday)] ||= []) << date.day
      date = date.succ
    end
  end

  def day_name(week_day_index)
    Date::DAYNAMES[week_day_index].downcase.to_sym
  end
end
