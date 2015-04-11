class Gigasecond
  def self.from(start_date)
    Time.at(1000000000 + start_date.to_time.to_i).to_date
  end
end
