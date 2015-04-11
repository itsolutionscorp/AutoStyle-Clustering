require 'date'

class Meetup
  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    weeks = days.select { |d| d.send("#{weekday}?") }
    find_week.fetch(schedule).call(weeks)
  end

  private

  def find_week
    { :first  => lambda { |weeks| weeks[0] },
      :second => lambda { |weeks| weeks[1] },
      :third  => lambda { |weeks| weeks[2] },
      :fourth => lambda { |weeks| weeks[3] },
      :last   => lambda { |weeks| weeks[-1] },
      :teenth => lambda { |weeks| teenth(weeks) }
    }
  end

  def teenth(week)
    week.find { |week| week.day.between?(13,19) }
  end

  def days
    Date.new(year,month,1)..Date.new(year,month,-1)
  end
end
