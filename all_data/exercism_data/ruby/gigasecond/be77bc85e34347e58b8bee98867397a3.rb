class Gigasecond
  def self.from(date)
    return date + 1000000000/24/60/60 if date.is_a? Date
    return (date + 1000000000).to_date if date.is_a? Time
  end
end
