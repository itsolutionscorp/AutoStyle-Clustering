require 'date'

class Meetup
  def initialize(month, year)
    @month = month
    @year  = year
  end

  def day(weekday, schedule)
    candidates = day_candidates(weekday)

    case schedule
    when :first then candidates.first
    when :second then candidates[1]
    when :third  then candidates[2]
    when :fourth then candidates[3]
    when :last then candidates.last
    when :teenth
      candidates.select { |date| date.day.between?(13, 19) }.first
    end
  end

  private

  attr_reader :month, :year

  def day_candidates(weekday)
    date = Date.new(year, month, 1)
    (date...date.next_month).select(&"#{weekday}?".to_sym)
  end
end
