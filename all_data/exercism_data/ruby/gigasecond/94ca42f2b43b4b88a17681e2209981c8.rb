class Gigasecond
  def self.from(date)
    @date1 = date
    find_date(@date1)
  end
  # finds number of full days in a gigasecond
  def self.gigasecond_to_day
    gigasecond = 10**9
    mins = gigasecond/60.0
    hours = mins/60.0
    days = (hours/24.0).to_i
  end
  # returns the date that is one gigasecond after the date object passed in as an argument
  def self.find_date(date)
    next_day = date.next_day
    (gigasecond_to_day - 1).times do 
      next_day = next_day.next_day
    end
    next_day
  end
end
