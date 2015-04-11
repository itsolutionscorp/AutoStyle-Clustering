class Meetup < Struct.new(:month, :year)
  # Seems like an appropriate use of method_missing
  def method_missing(method, *args, &block)
    case method.to_s
    when /(.+)teenth$/
      # seems difficult to make this more readable
      find_teenth(WDAY_FOR["#{$1}day".to_sym])
    when /^(first|second|third|fourth|last)_(.+)/
      send("#{$1}_finder", WDAY_FOR[$2.to_sym])
    else
      super
    end
  end

  def find_teenth  (wday)  finder(13, wday)  end
  def first_finder (wday)  finder( 1, wday)  end
  def second_finder(wday)  finder( 8, wday)  end
  def third_finder (wday)  finder(15, wday)  end
  def fourth_finder(wday)  finder(22, wday)  end
  def last_finder  (wday)  finder(-1, wday, -1) end

  def finder(starting_day, wday_to_find, step=1)
    current_date = Date.new(year, month, starting_day)
    while current_date.wday != wday_to_find
      current_date += step
    end
    current_date
  end

  WDAY_FOR = {
    :sunday    => 0,
    :monday    => 1,
    :tuesday   => 2,
    :wednesday => 3,
    :thursday  => 4,
    :friday    => 5,
    :saturday  => 6
  }
end
