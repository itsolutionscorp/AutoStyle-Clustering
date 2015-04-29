class Meetup

  require 'date'

  SCHEDULE = {:first => 0, 
              :second => 1, 
              :third => 2, 
              :fourth => 3, 
              :last => -1}

  VALID_DAYS = [:monday, :tuesday, :wednesday, :thursday, :friday, :saturday, :sunday]

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
      if VALID_DAYS.include?(day_of_week)
        day.send "#{day_of_week}?"
      else
        raise ArgumentError
      end
    end
  end

  def day(day_of_week, schedule)
    all_particular_days = find(day_of_week)
    if schedule == :teenth 
      selected_day = all_particular_days.find { |day| day.day >= 13 && day.day <= 19 }
    else
      selected_day = SCHEDULE[schedule]
      all_particular_days[selected_day]
    end
  end

end
