require 'date'
class Meetup
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
    Date.new(year, month, -1 - rev_offset(wkday, -1))
  end

  def teenth(wkday)
    Date.new(year, month, 13 + offset(wkday,13))
  end
  
  def rev_offset(wkday, baseday)
    (7 - day_delta(wkday, baseday)) % 7
  end

  def offset(wkday, baseday)
    (7 + day_delta(wkday, baseday)) % 7
  end

  def day_delta(wkday, baseday)
    WEEKDAYS[wkday] - Date.new(year, month, baseday).wday
  end
end
