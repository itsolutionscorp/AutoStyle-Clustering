class Gigasecond
  def self.from time
    Time.at(time.to_time.to_i + 10**9).to_date
  end
end
