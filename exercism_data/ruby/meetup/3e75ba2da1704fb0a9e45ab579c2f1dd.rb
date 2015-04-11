# meetup.rb
class Meetup
  attr_reader :date

  def initialize(month, year)
    @date = Date.new(year, month)
  end

  def day(weekday, schedule)
    filter_weekdays(find_weekdays(weekday), schedule)
  end

  private

  def filter_weekdays(weekdays, schedule)
    return teenth_days(weekdays) if schedule == :teenth
    weekdays[ordinals.index(schedule) - 1]
  end

  def teenth_days(weekdays)
    weekdays.find do |weekday|
      weekday.day.between?(13, 19)
    end
  end

  def ordinals
    [:last, :first, :second, :third, :fourth]
  end

  def find_weekdays(weekday)
    (date...date.next_month).select(&"#{weekday}?".to_sym)
  end
end
