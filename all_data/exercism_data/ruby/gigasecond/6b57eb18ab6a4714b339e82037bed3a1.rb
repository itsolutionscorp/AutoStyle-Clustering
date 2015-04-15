class Gigasecond
  def self.from date
    Date.parse((Time.parse(date.to_s) + (10**9)).to_s)
  end
end
