class Gigasecond
  # Iteration 1
  def self.from start_day
    (start_day.to_time + 10e8).to_date
  end
end
