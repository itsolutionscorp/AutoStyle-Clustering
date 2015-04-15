class Meetup
  def initialize(month, year)
    @month, @year = month, year
  end

  def day(weekday, schedule)
    potential_dates(schedule).find { |date| date.send("#{weekday}?") }
  end

private

  SCHEDULES_FIRST_DAYS = { first:   1, 
                           second:  8,
                           third:  15,
                           fourth: 22,
                           teenth: 13,
                           last:   -7 }

  SCHEDULES_FIRST_DAYS.each do |schedule, first_day|
    define_method(schedule) do 
      create_dates week_starting_on(first_day)
    end
  end                           

  def potential_dates(schedule)
    send(schedule)
  end

  def create_dates(days)
    days.map { |day| Date.new(@year, @month, day) }
  end

  def week_starting_on(day)
    day..day + 6
  end
end
