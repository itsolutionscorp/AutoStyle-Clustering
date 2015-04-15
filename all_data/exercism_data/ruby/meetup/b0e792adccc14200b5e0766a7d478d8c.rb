require 'date'

class Meetup
  attr_reader :month, :year

  LOOKUP = { :teenth => [*13..19], :first  => [*1..7],   :second => [*8..14],
             :third  => [*15..21], :fourth => [*22..28] }

  WDAY   = { :sunday   => 0, :monday => 1, :tuesday  => 2, :wednesday => 3, 
             :thursday => 4, :friday => 5, :saturday => 6 }

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    schedule == :last ? last(weekday) : lookup(weekday, schedule)
  end

  private

  def last(weekday)
    last_day = Date.new(year, month, 1).next_month.prev_day
    until last_day.wday == WDAY[weekday]
      last_day = last_day.prev_day
    end
    last_day
  end

  def lookup(weekday, schedule)
    LOOKUP[schedule].each do |day| 
      date = Date.new(year, month, day)
      return date if date.wday == WDAY[weekday]
    end
  end
end
