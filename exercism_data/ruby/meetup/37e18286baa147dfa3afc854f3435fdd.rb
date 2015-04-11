class Meetup
  TEENS = (13..19)

  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    candidates = date_candidates_for(weekday)
    the_day = find_week.fetch(schedule).call(candidates)
    Date.new(year, month, the_day)
  end

  private

  def date_candidates_for(weekday)
    (1..month_length).select do |date|
      Date.new(year, month, date).public_send("#{weekday}?")
    end
  end

  def month_length
    Date.new(year, month, -1).day
  end

  def find_week
    { :first  => lambda { |days| days[0] },
      :second => lambda { |days| days[1] },
      :third  => lambda { |days| days[2] },
      :fourth => lambda { |days| days[3] },
      :last   => lambda { |days| days[-1] },
      :teenth => lambda { |days| find_teenth(days) }
    }
  end

  def find_teenth(days)
    days.find { |day| TEENS.include?(day) }
  end

end
