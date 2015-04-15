class Gigasecond

  def self.from(input)

    seconds_to_days = 10**9/60/60/24

    if input.class == Date
      input + seconds_to_days
    elsif input.class == Time
      new_date = input + 10**9
      Date.new(new_date.year, new_date.month, new_date.day)
    end
    
  end
  
end
