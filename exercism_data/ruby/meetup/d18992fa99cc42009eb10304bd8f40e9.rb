require 'date'

class Meetup
  def initialize(month, year)
    self.month = month
    self.year  = year
  end

  def monteenth()    teens.find(&:monday?)    end
  def tuesteenth()   teens.find(&:tuesday?)   end
  def wednesteenth() teens.find(&:wednesday?) end
  def thursteenth()  teens.find(&:thursday?)  end
  def friteenth()    teens.find(&:friday?)    end
  def saturteenth()  teens.find(&:saturday?)  end
  def sunteenth()    teens.find(&:sunday?)    end

  def first_monday()    mondays[0]    end
  def first_tuesday()   tuesdays[0]   end
  def first_wednesday() wednesdays[0] end
  def first_thursday()  thursdays[0]  end
  def first_friday()    fridays[0]    end
  def first_saturday()  saturdays[0]  end
  def first_sunday()    sundays[0]    end

  def second_monday()    mondays[1]    end
  def second_tuesday()   tuesdays[1]   end
  def second_wednesday() wednesdays[1] end
  def second_thursday()  thursdays[1]  end
  def second_friday()    fridays[1]    end
  def second_saturday()  saturdays[1]  end
  def second_sunday()    sundays[1]    end

  def third_monday()    mondays[2]    end
  def third_tuesday()   tuesdays[2]   end
  def third_wednesday() wednesdays[2] end
  def third_thursday()  thursdays[2]  end
  def third_friday()    fridays[2]    end
  def third_saturday()  saturdays[2]  end
  def third_sunday()    sundays[2]    end

  def fourth_monday()    mondays[3]    end
  def fourth_tuesday()   tuesdays[3]   end
  def fourth_wednesday() wednesdays[3] end
  def fourth_thursday()  thursdays[3]  end
  def fourth_friday()    fridays[3]    end
  def fourth_saturday()  saturdays[3]  end
  def fourth_sunday()    sundays[3]    end

  def last_monday()    mondays[-1]    end
  def last_tuesday()   tuesdays[-1]   end
  def last_wednesday() wednesdays[-1] end
  def last_thursday()  thursdays[-1]  end
  def last_friday()    fridays[-1]    end
  def last_saturday()  saturdays[-1]  end
  def last_sunday()    sundays[-1]    end

  private
  attr_accessor :month, :year

  def dates
    @dates ||= begin
                end_day = start_day = Date.new(year, month, 1)
                end_day = end_day.next until end_day.next.month != month
                start_day..end_day
              end
  end

  def teens
    @teens ||= dates.select { |date| (13..19).cover? date.day }
  end

  def mondays()    @mondays    ||= dates.select(&:monday?)    end
  def tuesdays()   @tuesdays   ||= dates.select(&:tuesday?)   end
  def wednesdays() @wednesdays ||= dates.select(&:wednesday?) end
  def thursdays()  @thursdays  ||= dates.select(&:thursday?)  end
  def fridays()    @fridays    ||= dates.select(&:friday?)    end
  def saturdays()  @saturdays  ||= dates.select(&:saturday?)  end
  def sundays()    @sundays    ||= dates.select(&:sunday?)    end
end
