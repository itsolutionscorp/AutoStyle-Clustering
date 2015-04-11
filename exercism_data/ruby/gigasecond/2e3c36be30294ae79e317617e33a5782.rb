class Gigasecond
  
  def initialize date
    @date = date
  end
  
  def date
    @date + no_days_per_gigasecond
  end
  
  private
  
  def minute
    60
  end
  
  def hour
    minute * 60
  end
  
  def day
    hour * 24
  end
  
  def no_days_per_gigasecond
    gigasecond / day
  end
  
  def gigasecond
    10 ** 9
  end
  
end
