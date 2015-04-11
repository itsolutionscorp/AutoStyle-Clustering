class Meetup
  require 'date'

  WEEKDAYS = Date::DAYNAMES.map{ |name| name.downcase.to_sym }

  def initialize(month, year)
    @month = month
    @year = year
    @frame_first_day = {
      first: 1, 
      second: 8, 
      third: 15,
      fourth: 22,
      teenth: 13,
      last: last_day_of_month - 6
    }
  end

  def day(weekday, frame)
    get_date(WEEKDAYS.index(weekday), @frame_first_day[frame])
  end

  private
  
    def get_date(weekday_number, first_day_for_frame)
      start_date = Date.new(@year, @month, first_day_for_frame)
      offset_days = (weekday_number - start_date.wday) % 7
      start_date + offset_days
    end

    def last_day_of_month
      Date.new(@year, @month, -1).day
    end

end
