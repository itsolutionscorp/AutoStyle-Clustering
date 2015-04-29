class Gigasecond
  def self.from(date)
    return date + (10**9/3600/24) if date.is_a?(Date)
    return Date.parse (date + 10**9).to_s if date.is_a?(Time)
  end
end
