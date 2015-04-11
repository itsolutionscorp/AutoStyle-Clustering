class Clock
  def Clock.at(hour, minute=0)
    time = ""
    if hour < 10
      time += "0#{hour}:"
    else
      time += "#{hour}:"
    end
    time += "00" if minute == 0
    if minute < 10 && minute > 0
      time += "0#{minute}"
    elsif minute != 0
      time += "#{minute}"
    end
    time
  end
end
