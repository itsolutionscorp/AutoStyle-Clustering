class Meetup
  require 'time'

  def initialize(mon, year)
    @mon = mon
    @year = year
  end

  def get_date(wday, start, iter)
    d = Date.new(@year, @mon, start)
    while(d.wday != wday)
      d += iter
    end
    d
  end

  def method_missing(name, *args, &block)
    # defaults
    wday = 0  # sunday
    start = 1 # first
    iter = 1  # search forward
    case
    when name =~ /mon/
      wday = 1
    when name =~ /tue/
      wday = 2
    when name =~ /wed/
      wday = 3
    when name =~ /thu/
      wday = 4
    when name =~ /fri/
      wday = 5
    when name =~ /sat/
      wday = 6
    end
    case
    when name =~ /teenth/
      start = 13
    when name =~ /second/
      start = 8
    when name =~ /third/
      start = 15
    when name =~ /fourth/
      start = 22
    when name =~ /last/
      start = -1
      iter = -1
    end      
    get_date(wday, start, iter)
  end

end
