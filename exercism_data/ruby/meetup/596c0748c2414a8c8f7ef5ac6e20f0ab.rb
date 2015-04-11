class Meetup

  def initialize(month, year)
    @month = month
    @year  = year
  end

  def day(weekday, schedule)
    wkday = weekdays.index(weekday)

    return find_teenth_day(wkday) if schedule.eql? :teenth

    s = schedules.index(schedule) || -1
    find_matching_days(wkday)[s]
  end

  private

  def weekdays
    [:sunday, :monday, :tuesday, :wednesday, :thursday, :friday, :saturday]
  end

  def schedules
    [:first, :second, :third, :fourth]
  end

  def find_matching_days(weekday)
    date = Date.new(@year, @month)
    date.upto(date.next_month.prev_day)
        .each_with_object([]) do |date,matches|
           matches << date if date.wday == weekday
    end
  end

  def find_teenth_day(weekday)
    date = Date.new(@year, @month, 13)
    date.upto(date + 6).select do |date|
      date.wday == weekday
    end.at(0)
  end

end
