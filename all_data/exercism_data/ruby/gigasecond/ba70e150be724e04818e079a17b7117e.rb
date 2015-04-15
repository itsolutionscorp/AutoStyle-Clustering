class Gigasecond
  @@giga_second = 10**9

  def initialize date
    
    @time = date.to_time

  end

  def date
     (@time + @@giga_second).to_date
  end

end
