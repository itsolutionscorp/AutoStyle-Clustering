class Meetup < Struct.new(:month, :year)
  # Seems like an appropriate use of method_missing
  def method_missing(method, *args, &block)
    case method.to_s
    when /(.+)teenth$/
      # seems difficult to make this more readable
      find_teenth(WEEKDAY_FOR["#{$1}day".to_sym])
    when /^(first|second|third|fourth|last)_(.+)/
      send("#{$1}_finder", WEEKDAY_FOR[$2.to_sym])
    else
      super
    end
  end

  def find_teenth  (weekday)  finder(13, weekday)     end
  def first_finder (weekday)  finder( 1, weekday)     end
  def second_finder(weekday)  finder( 8, weekday)     end
  def third_finder (weekday)  finder(15, weekday)     end
  def fourth_finder(weekday)  finder(22, weekday)     end
  def last_finder  (weekday)  finder(-1, weekday, -1) end

  def finder(starting_day, weekday_to_find, step=1)
    current_date = Date.new(year, month, starting_day)
    while current_date.wday != weekday_to_find
      current_date += step
    end
    current_date
  end

  WEEKDAY_FOR = {
    :sunday    => 0,
    :monday    => 1,
    :tuesday   => 2,
    :wednesday => 3,
    :thursday  => 4,
    :friday    => 5,
    :saturday  => 6
  }
end
