class Meetup
  def initialize(day, year)
    @month = day
    @year = year
  end

  def day(weekday, kind_of_day)
    date = Date.new(@year, @month, find_start[kind_of_day])
    until date.__send__ "#{weekday}?"
      date += 1
    end
    date
  end

  def find_start
     {
       teenth: 13, first: 1, second: 8, third: 15, fourth: 22,
       last: Date.new(@year, @month, -1).day - 6
     }
  end
end
