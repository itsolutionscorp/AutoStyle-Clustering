require 'date'

class Meetup

  def initialize month, year
    @date = Date.new(year, month, 1 )
  end

  def find_date(date, wday)
    if date.wday == wday
      return date
    elsif date.wday < wday
      date = date + ( wday - date.wday )
    else 
      date = date + ( 7 - date.wday )
      date = date + wday
    end
    return date
  end


  def teenth(wday)
    return find_date( @date + 12, wday )
  end


  def find_the_first(wday)
    return find_date( @date, wday )
  end

  def find_the_second(wday)
    return find_date( @date + 7, wday )
  end

  def find_the_third(wday)
    return find_date( @date + 14, wday )
  end

  def find_the_fourth(wday)
    return find_date( @date + 21, wday )
  end

  def find_last_day(wday)
    @last_day = @date.next_month - 1
    
    if @last_day.wday == wday
      return @last_day
    elsif @last_day.wday < wday
      find_the_fourth(wday)
    else
      return @last_day - (@last_day.wday - wday )
   end

  end

  def monteenth
    return teenth(1)
  end

  def tuesteenth
    return teenth(2)
  end

  def wednesteenth
    return teenth(3)
  end

  def thursteenth
    return teenth(4)
  end

  def friteenth
    return teenth(5)
  end

  def saturteenth
    return teenth(6)
  end

  def sunteenth
    return teenth(0)
  end

  def first_monday
    find_the_first(1)
  end

  def first_tuesday
    find_the_first(2)
  end

  def first_wednesday
    find_the_first(3)
  end

  def first_thursday
    find_the_first(4)
  end

  def first_friday
    find_the_first(5)
  end

  def first_saturday
    find_the_first(6)
  end

  def first_sunday
    find_the_first(0)
  end

  def second_monday
    find_the_second(1)
  end

  def second_tuesday
    find_the_second(2)
  end

  def second_wednesday
    find_the_second(3)
  end

  def second_thursday
    find_the_second(4)
  end

  def second_friday
    find_the_second(5)
  end

  def second_saturday
    find_the_second(6)
  end

  def second_sunday
    find_the_second(0)
  end

  def third_monday
    find_the_third(1)
  end

  def third_tuesday
    find_the_third(2)
  end

  def third_wednesday
    find_the_third(3)
  end

  def third_thursday
    find_the_third(4)
  end

  def third_friday
    find_the_third(5)
  end

  def third_saturday
    find_the_third(6)
  end

  def third_sunday
    find_the_third(0)
  end

  def fourth_monday
    find_the_fourth(1)
  end

  def fourth_tuesday
    find_the_fourth(2)
  end

  def fourth_wednesday
    find_the_fourth(3)
  end

  def fourth_thursday
    find_the_fourth(4)
  end

  def fourth_friday
    find_the_fourth(5)
  end

  def fourth_saturday
    find_the_fourth(6)
  end

  def fourth_sunday
    find_the_fourth(0)
  end

  def last_monday
    find_last_day(1)
  end

  def last_tuesday
    find_last_day(2)
  end

  def last_wednesday
    find_last_day(3)
  end

  def last_thursday
    find_last_day(4)
  end

  def last_friday
    find_last_day(5)
  end

  def last_saturday
    find_last_day(6)
  end

  def last_sunday
    find_last_day(0)
  end


end
