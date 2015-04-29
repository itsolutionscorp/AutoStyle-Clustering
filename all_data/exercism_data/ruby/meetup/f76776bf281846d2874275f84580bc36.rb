class Meetup
  def initialize(day, year)
    @month = day
    @year = year
  end

  def day(weekday, kind_of_day)
    date = Date.new(@year, @month, find_start(kind_of_day))
    until date.__send__ "#{weekday}?"
      date += 1
    end
    date
  end

  def find_start(kind_of_day)
    case kind_of_day
    when :teenth  then 13
    when :first   then 1
    when :second  then 8
    when :third   then 15
    when :fourth  then 22
    when :last    then Date.new(@year, @month, -1).day - 6
    end
  end
end
