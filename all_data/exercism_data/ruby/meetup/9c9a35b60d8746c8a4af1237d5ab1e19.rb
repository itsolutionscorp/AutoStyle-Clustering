class Meetup
  def initialize(month, year)
    @month = month
    @year = year
    @ranges = {
      :teenth => (13..19),
      :first => (1..7),
      :second => (8..14),
      :third => (15..21),
      :fourth => (22..28),
      :last => get_last_week
    }
  end

  def day(day, schedule)
    @ranges[schedule].each do |d|
      date = Date.new(@year, @month, d)
      return date if date.strftime('%A') == day.to_s.capitalize
    end
  end

  def get_last_week(last_day = 31)
    begin
      Date.new(@year, @month, last_day)
      (last_day - 6..last_day)
    rescue
      get_last_week(last_day - 1)
    end
  end
end
