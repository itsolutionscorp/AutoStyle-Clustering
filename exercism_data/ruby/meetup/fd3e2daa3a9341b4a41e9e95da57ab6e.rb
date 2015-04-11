require 'date'

class Meetup
  def initialize(m, y)
    @m, @y = m, y
    @weekdays = [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday,
                 :saturday]
    @startDay = { :first => 1, :second => 8, :third => 15, :fourth => 22,
                  :teenth => 13 }
  end

  def day(day_of_week, type)
    if type == :last
      mon_len = Date.new(@y, @m, 1).next_month.prev_day.day
      date = Date.new(@y, @m, mon_len)
      date = find(date, day_of_week, last=true)
    else
      raise ValueError unless @startDay.key?(type)
      date = Date.new(@y, @m, @startDay[type])
      date = find(date, day_of_week)
    end
  end

  private

  def find(date, day_of_week, last=false)
    weekday_req = @weekdays.index(day_of_week)
    if last
      while date.wday != weekday_req
        date = date.prev_day
      end
    else
      diff = (weekday_req - date.wday) % 7
      date += diff
    end
    date
  end
end
