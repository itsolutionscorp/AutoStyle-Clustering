class Gigasecond
  def self.from(input_date)
    Time.at(input_date.to_time.to_i + 10**9).to_date
  end
end
