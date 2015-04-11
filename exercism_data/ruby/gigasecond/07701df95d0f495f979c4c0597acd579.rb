class Gigasecond

  def initialize (date)
    @tob = date.to_time  
  end

  def date
    (@tob + 1000000000).to_date
  end

end
