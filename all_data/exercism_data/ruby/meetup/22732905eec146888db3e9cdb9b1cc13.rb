class Meetup
  WDAYS = [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]
  ORDER = {:first => 0, :second => 1, :third => 2, :fourth => 3, :last => -1}

  def initialize month, year
    @month = month
    @year = year
  end

  def find_day weekday
    last = Date.new(@year, @month, -1).mday
    (1..last).each.with_object([]) {|d, arr|
      this_date = Date.new(@year, @month, d)
      arr << this_date if WDAYS[this_date.wday] == weekday
    }
  end

  def day weekday, modifier
    if modifier == :teenth
      (13..19).each {|x|
        this_date = Date.new(@year, @month, x)
        return this_date if WDAYS[this_date.wday] == weekday
      }
    end
    return find_day(weekday)[ORDER[modifier]]
  end

end
