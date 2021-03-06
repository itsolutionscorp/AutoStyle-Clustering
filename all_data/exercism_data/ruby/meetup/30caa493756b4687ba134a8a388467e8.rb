class Meetup

  require 'date'

  SCHEDULE = {:first => 0, 
              :second => 1, 
              :third => 2, 
              :fourth => 3, 
              :last => -1, 
              :teenth => 0}

  def initialize(month, year)
    @month = month
    @year = year
  end

  def find(day_of_week)
    month_array = []
    current_date = Date.new(@year, @month)
    while current_date.month == @month
      month_array << current_date
      current_date += 1
    end
    month_array.select do |day|
      day.send "#{day_of_week}?"
    end
  end

  def day(day_of_week, schedule)
    all_specific_weekdays = find(day_of_week)
    if schedule == :teenth 
      selected_day = all_specific_weekdays.select do |day|
        day.day >= 13 && day.day <= 19
      end
      selected_day[0]
    else
      selected_day = SCHEDULE[schedule]
      all_specific_weekdays[selected_day]
    end
  end

end
