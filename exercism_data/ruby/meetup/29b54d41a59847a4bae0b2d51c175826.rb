class Meetup
  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    days = find_weekdays(weekday)

    case schedule
    # teenths: 13th, 14th, 15th, 16th, 17th, 18th, 19th
    when :teenth then days.select { |d| (13..19).cover? d.day }.first
    when :first then days.first
    when :second then days[1]
    when :third then days[2]
    when :fourth then days[3]
    when :last then days.last
    end
  end

  private

  attr_reader :month, :year

  def find_weekdays(weekday)
    days_of_the_month.select { |d| d.send(weekday.to_s + '?') }
  end

  def days_of_the_month
    d0_this_month = Date.new(year, month)
    d0_next_month = d0_this_month.next_month

    d0_this_month...d0_next_month
  end
end
