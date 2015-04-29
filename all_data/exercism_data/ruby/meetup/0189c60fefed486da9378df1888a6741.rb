class Meetup
  TEENS = (13..19)

  attr_reader :month, :year

  def initialize(month, year)
    @month = month
    @year = year
  end

  def day(weekday, schedule)
    candidates = date_candidates_for(weekday)
    the_day = find_week[schedule].call(candidates)
    Date.new(year, month, the_day)
  end

  private

  def date_candidates_for(weekday)
    (1..month_length).to_a.select do |date|
      Date.new(year, month, date).send("#{weekday}?".to_sym)
    end
  end

  def month_length
    return 28 if month == 2
    [4, 6, 9, 11].include?(month) ? 30 : 31
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
    days.select{ |day| TEENS.include?(day) }.first
  end

end
