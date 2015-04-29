class Gigasecond
  def self.from(date)
    current_date_seconds = date.to_time.to_i
    Time.at(current_date_seconds + 10**9).to_date
  end
end
