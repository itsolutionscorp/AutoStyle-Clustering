class Meetup

  attr_reader :year, :month, :day

  def initialize(month, year, day=0)
    @month = month
    @year = year
    @day = day
  end

  def monteenth
    find_day('monday', teenth_week)
  end

  def tuesteenth
    find_day('tuesday', teenth_week)
  end

  def wednesteenth
    find_day('wednesday', teenth_week)
  end

  def thursteenth
    find_day('thursday', teenth_week)
  end

  def friteenth
    find_day('friday', teenth_week)
  end

  def saturteenth
    find_day('saturday', teenth_week)
  end

  def sunteenth
    find_day('sunday', teenth_week)
  end

  def first_monday
    find_day('monday', first_week)
  end

  def first_tuesday
    find_day('tuesday', first_week)
  end

  def first_wednesday
    find_day('wednesday', first_week)
  end

  def first_thursday
    find_day('thursday', first_week)
  end

  def first_friday
    find_day('friday', first_week)
  end

  def first_saturday
    find_day('saturday', first_week)
  end

  def first_sunday
    find_day('sunday', first_week)
  end

  def second_monday
    find_day('monday', second_week)
  end

  def second_tuesday
    find_day('tuesday', second_week)
  end

  def second_wednesday
    find_day('wednesday', second_week)
  end

  def second_thursday
    find_day('thursday', second_week)
  end

  def second_friday
    find_day('friday', second_week)
  end

  def second_saturday
    find_day('saturday', second_week)
  end

  def second_sunday
    find_day('sunday', second_week)
  end

  def third_monday
    find_day('monday', third_week)
  end

  def third_tuesday
    find_day('tuesday', third_week)
  end

  def third_wednesday
    find_day('wednesday', third_week)
  end

  def third_thursday
    find_day('thursday', third_week)
  end

  def third_friday
    find_day('friday', third_week)
  end

  def third_saturday
    find_day('saturday', third_week)
  end

  def third_sunday
    find_day('sunday', third_week)
  end

  def fourth_monday
    find_day('monday', fourth_week)
  end

  def fourth_tuesday
    find_day('tuesday', fourth_week)
  end

  def fourth_wednesday
    find_day('wednesday', fourth_week)
  end

  def fourth_thursday
    find_day('thursday', fourth_week)
  end

  def fourth_friday
    find_day('friday', fourth_week)
  end

  def fourth_saturday
    find_day('saturday', fourth_week)
  end

  def fourth_sunday
    find_day('sunday', fourth_week)
  end

  def last_monday
    find_day('monday', last_week)
  end

  def last_tuesday
    find_day('tuesday', last_week)
  end

  def last_wednesday
    find_day('wednesday', last_week)
  end

  def last_thursday
    find_day('thursday', last_week)
  end

  def last_friday
    find_day('friday', last_week)
  end

  def last_saturday
    find_day('saturday', last_week)
  end

  def last_sunday
    find_day('sunday', last_week)
  end

  private

  def find_day(day, week)
    start_date = Date.new(year, month, week.first)
    end_date = Date.new(year, month, week.last)
    start_date.upto(end_date).select {|d| d.wday == weekdays[day] }.pop
  end

  def first_week
    days = 1..7
  end

  def second_week
    days = 8..14
  end

  def teenth_week
    days = 13..19
  end

  def third_week
    days = 15..21
  end

  def fourth_week
    days = 22..28
  end

  def last_week
    days = -7..-1
  end

  def weekdays
    { 'sunday' => 0,
      'monday' => 1,
      'tuesday' => 2,
      'wednesday' => 3,
      'thursday' => 4,
      'friday' => 5,
      'saturday' => 6
    }
  end

end
