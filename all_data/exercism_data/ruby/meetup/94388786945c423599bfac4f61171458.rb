require 'date'

class Meetup
  METHODS = {:first => 1, :second => 2, :third => 3, :fourth => 4}

  METHODS.each_pair do |key,value|
    define_method(key) do |arg|
      value.times{|x| calculate_day(arg); @date += 1 if x < value - 1}
    end
  end

  define_method(:teenth){|arg| @date += 12; calculate_day(arg)}

  def initialize(month, year)
    @date = Date.new(year, month)
  end

  def day(weekday, schedule)
    if self.respond_to?(schedule)
      self.send(schedule, weekday)
    else
      raise NoMethodError, "now's the path to Enlightenment"
    end
    return @date
  end

  def last(weekday)
    m = @date.month
    begin
      @date += 1
    end until @date.month != m
    @date -= 7
    calculate_day(weekday)
  end

  def calculate_day(weekday)
    until @date.send("#{weekday}?".to_sym)
      @date += 1
    end
  end
end
