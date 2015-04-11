class Meetup

  def initialize(month, year)
    @date = Date.new(year, month)
  end

  def day(weekday, schedule)
    case schedule
    when :teenth then first(weekday, starting_with: 13)
    when :last then last(weekday)
    else 
      position = [:first, :second, :third, :fourth]
      first(weekday) + (position.index(schedule) * 7)
    end
  end

  private
  def first(weekday, starting_with: 1, of_date: @date)
    day = of_date + (starting_with - 1)
    while !day.public_send "#{weekday}?"
      day += 1
    end
    day
  end
  
  def last(weekday)
    first(weekday, of_date: @date.next_month) - 7
  end
end
