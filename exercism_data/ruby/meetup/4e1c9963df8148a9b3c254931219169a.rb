require 'date'

class Array
  { second: 1, third: 2, fourth: 3 }.each do |name, index|
    define_method(name) { self[index] }
  end

  def teenth
    find { |date| date.day.between?(13, 19) }
  end
end

class Meetup
  def initialize(month, year)
    Date.new(year, month).tap do |d|
      @dates = Array(d...d.next_month)
    end
  end

  def day(weekday, schedule)
    @dates.select(&:"#{weekday}?").send(schedule)
  end
end
