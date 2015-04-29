class Gigasecond
  def self.from(date)
    Time.at(date.strftime('%s').to_i + 1_000_000_000)
  end
end
