require 'date'

class Meetup
  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    weekdays = days.select { |d| d.send("#{weekday}?") }
    find_week.fetch(schedule).call(weekdays)
  end

  private

  def find_week
    { :first  => lambda { |week| week[0] },
      :second => lambda { |week| week[1] },
      :third  => lambda { |week| week[2] },
      :fourth => lambda { |week| week[3] },
      :last   => lambda { |week| week[-1] },
      :teenth => lambda { |week| teenth(week) }
    }
  end

  def teenth(week)
    week.find { |week| week.day.between?(13,19) }
  end

  def days
    Date.new(year,month,1)..Date.new(year,month,-1)
  end
end
