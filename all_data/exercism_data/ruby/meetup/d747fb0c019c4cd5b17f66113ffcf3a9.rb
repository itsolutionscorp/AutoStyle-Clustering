class Meetup
  def initialize(month, year)
    @month, @year = month, year
  end

  def day(weekday, schedule)
    case schedule
    when :first
      find_date_in_range(weekday, 1, 7)
    when :second
      find_date_in_range(weekday, 8, 14)
    when :third
      find_date_in_range(weekday, 15, 21)
    when :fourth
      find_date_in_range(weekday, 22, 28)
    when :teenth
      find_date_in_range(weekday, 13, 19)
    when :last
      last = Date.new(@year, @month, -1)
      find_date_in_range(weekday, (last - 6).day, last.day)
    end
  end

  private

  def find_date_in_range(weekday, first_day, last_day)
    Date.new(@year, @month, first_day).upto(Date.new(@year, @month, last_day)).detect { |date| date.strftime("%A").downcase == weekday.to_s }
  end
end
