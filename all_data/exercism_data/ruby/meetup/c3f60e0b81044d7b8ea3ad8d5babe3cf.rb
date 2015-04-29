class Meetup
  def initialize(month, year)
    @month, @year = month, year
  end

  def day(day, schedule)
    proc_for(schedule).(dates_for day)
  end

  private

  attr_reader :month, :year

  DAYS_OF_WEEK = %i[ sunday monday tuesday wednesday thursday friday saturday ].freeze
  DEFAULT_PROC = -> (schedule) { -> (ary) { ary.send(schedule) } }
  INDEX        = -> (n) { -> (ary) { ary[n] } }
  SCHEDULE     = {
    second: INDEX[1],
    third:  INDEX[2],
    fourth: INDEX[3],
    teenth: -> (ary) { ary.detect {|d| (13..19).include?(d.mday) } }
  }

  def days_of_week
    @days_of_week = DAYS_OF_WEEK.dup.tap do |ary|
      first_day_of_month_weekday_index.times { ary.push ary.shift }
    end.cycle
  end

  def first_day_of_month
    @fdom ||= Date.new(year, month)
  end

  def month_index
    @month_index ||= first_day_of_month.month
  end

  def first_day_of_month_weekday_index
    first_day_of_month.wday
  end

  def days_in_month
    (1..31).map do |day| 
      Date.new(year, month, day) rescue nil
    end.compact
  end

  def month_map
    @month_map ||= days_in_month.zip(days_of_week)
  end

  def dates_for(day)
    month_map.select {|_, wday| wday == day }.map(&:first)
  end

  def proc_for(schedule)
    SCHEDULE.fetch(schedule){ DEFAULT_PROC.(schedule) }
  end
end
