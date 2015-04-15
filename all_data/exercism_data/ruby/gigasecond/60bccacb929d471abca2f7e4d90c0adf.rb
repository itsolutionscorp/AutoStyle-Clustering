class Gigasecond
  def self.from(initial_date)
    Time.at(initial_date.to_time.to_i + (10**9)).to_date
  end
end
