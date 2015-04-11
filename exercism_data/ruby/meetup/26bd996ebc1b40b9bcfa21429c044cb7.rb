require 'date'

class Meetup
  WEEKDAYS = {
    :monday => 1, :tuesday => 2, :wednesday => 3, :thursday => 4,
    :friday => 5, :saturday => 6, :sunday => 7
  }
  ORDINALS = {
    :first => 1, :second => 2, :third => 3, :fourth => 4,
    :fifth => 5
  }

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    case schedule
    when :first, :second, :third, :fourth
      nth_of_weekday(weekday, ORDINALS[schedule])
    when :last
      last_of_weekday(weekday)
    when :teenth
      teenth_of_weekyday(weekday)
    end
  end

  private
  def nth_of_weekday(weekday, n)
    day = Date.new(@year, @month, 1)

    diff_days = case
    when ((WEEKDAYS[weekday] - day.cwday) >= 0)
      WEEKDAYS[weekday] - day.cwday
    else
      WEEKDAYS[weekday] + 7 - day.cwday
    end
    diff_days += (n - 1) * 7

    day + diff_days
  end

  def teenth_of_weekyday(weekday)
    [
      nth_of_weekday(weekday, ORDINALS[:second]),
      nth_of_weekday(weekday, ORDINALS[:third])
    ].select do |day|
      day.mday.between?(10, 19)
    end.max
  end

  def last_of_weekday(weekday)
    [
      nth_of_weekday(weekday, ORDINALS[:fourth]),
      nth_of_weekday(weekday, ORDINALS[:fifth])
    ].select do |day|
      day.month == @month
    end.max
  end
end
