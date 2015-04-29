class Gigasecond
  GIGASECOND = 10**9
  COMPENSATE_FOR_STANDARD_TIME = -3600

  def self.from(time)
    if time.year < 1970
      Time.at(time.to_i + GIGASECOND + COMPENSATE_FOR_STANDARD_TIME)
    else
      time + GIGASECOND
    end
  end

end
