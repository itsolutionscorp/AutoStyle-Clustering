class Gigasecond
  def self.from(date)
    Date.parse((Time.parse(date.to_s) + 1000000000).to_s)
  end
end
