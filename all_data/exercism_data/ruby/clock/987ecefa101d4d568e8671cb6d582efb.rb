class Clock
  def self.at(hour, minute="00")
    hour_array = hour.to_s.split(//)
    minute_array = minute.to_s.split(//)
    if hour_array.size == 1
      hour_array.unshift("0")
    elsif minute_array.size == 1
      minute_array.unshift("0")
    end
    "#{hour_array.join}:#{minute_array.join}"
  end

end
