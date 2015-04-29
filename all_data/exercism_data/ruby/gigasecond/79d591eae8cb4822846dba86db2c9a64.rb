class Gigasecond
  def self.from(input_date)
    (input_date.to_time + 10**9).to_date
  end
end
