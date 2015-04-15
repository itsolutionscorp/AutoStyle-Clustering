class Meetup

  def initialize(month, year)
    @month = month
    @year = year
  end

  def weekday_number(weekday)
    [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday].index(weekday)
  end


  def days_until(weekday, day)
    (weekday_number(weekday) - day.wday) % 7
  end

  def day(weekday, which)
    case which
    when :teenth then
      date = Date.new(@year, @month, 13)
    when :first then
      date = Date.new(@year, @month, 1)
    when :second then
      date = Date.new(@year, @month, 8)
    when :third then
      date = Date.new(@year, @month, 15)
    when :fourth then
      date = Date.new(@year, @month, 22)
    when :last then
      date = Date.new(@year, @month, -1)
      return date - (7 - (weekday_number(weekday) - date.wday)) % 7
    end
    date + days_until(weekday, date)
  end

end
