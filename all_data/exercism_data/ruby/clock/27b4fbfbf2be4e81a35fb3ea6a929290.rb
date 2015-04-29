class Clock
  def self.at(hour, minute=0)
    @total_minutes = hour * 60 + minute
    self
  end

  def self.+(minute)
    @total_minutes += minute
    self
  end

  def self.-(minute)
    @total_minutes -= minute
    self
  end

  def self.to_s
    hour = @total_minutes / 60
    hour -= 24 if hour>=24
    minutes = @total_minutes % 60
    time = ""

    if hour < 10
      time += "0#{hour}:"
    else
      time += "#{hour}:"
    end

    if minutes == 0
      time += "00"
    elsif minutes < 10
      time += "0#{minutes}"
    else
      time += "#{minutes}"
    end
    time
  end

end
