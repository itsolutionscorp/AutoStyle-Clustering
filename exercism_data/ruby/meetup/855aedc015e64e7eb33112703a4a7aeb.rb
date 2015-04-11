require 'date'
class Meetup
  MONTH_ADJ = {1 => 0, 2 => 3, 3 => 0, 4 => 1, 5 => 0, 6 => 1, 
               7 => 0, 8 => 0, 9 => 1, 10 => 0, 11 => 1, 12 => 0}
  WEEKDAYS = {sun:0, mon:1, tues:2, wednes:3, thurs:4, fri:5, satur:6}
  ORD_CARD = {'first' => 1, 'second' => 2, 'third' => 3, 'fourth' => 4}

  WEEKDAYS.keys.each do |day|
    ORD_CARD.each_pair do |ord,card|
      define_method("#{ord}_#{day.to_s}day") {ordinal day, card }
    end
    define_method("last_#{day.to_s}day") {last day }
    define_method("#{day.to_s}teenth") {teenth day }
  end

  def initialize(month, year)
    @month = month
    @year = year
  end

private
  attr_reader :month, :year

  def ordinal(wkday, pos)
    Date.new(year, month, 1 + offset(wkday,1) + (pos-1)*7)
  end

  def last(wkday)
    baseday = 25 - MONTH_ADJ[month]
    Date.new(year, month, baseday + offset(wkday,baseday))
  end

  def teenth(wkday)
    Date.new(year, month, 13 + offset(wkday,13))
  end
  

  def offset(wkday, baseday)
    (7+WEEKDAYS[wkday]-wday(month,year,baseday)).modulo 7
  end

  def wday(month,year,day)
    Date.new(year, month, day).wday
  end
end
