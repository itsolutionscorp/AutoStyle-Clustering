class Gigasecond
  def self.from(date)
    if date.is_a? Date
      date + (10**9/60/60/24)
    else
      (date.to_time + (10**9)).to_date
    end
  end
end
