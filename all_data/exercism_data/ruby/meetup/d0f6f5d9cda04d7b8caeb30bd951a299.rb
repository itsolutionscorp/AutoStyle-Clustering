class Meetup
  def initialize(month, year)
    @y = year
    @m = month
  end
  
  def first_wday_after(wday, d)
    # Sunday is zero
    w = d.wday
    d + (wday - w) % 7
  end
  
  def first_wday_after_d(wday, sday)
    first_wday_after(wday, Date.new(@y,@m,sday))
  end
  
  def last_wday(wday)
    d = Date.new(@y,@m,1).next_month - 7
    first_wday_after(wday, d)
  end
  
  {"sun"=>0,"mon"=>1,"tues"=>2,"wednes"=>3,"thurs"=>4,"fri"=>5,"satur"=>6}.each do |d,n|
    {"#{d}teenth"=>13,"first_#{d}day"=>1,"second_#{d}day"=>8,"third_#{d}day"=>15,"fourth_#{d}day"=>22}.each do |m,s|
      define_method(m.to_sym) { first_wday_after_d(n,s) }
    end
    define_method("last_#{d}day".to_sym) { last_wday(n) }
  end
end
