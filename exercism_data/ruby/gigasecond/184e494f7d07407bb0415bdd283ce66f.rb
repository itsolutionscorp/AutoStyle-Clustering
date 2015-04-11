class Gigasecond

  def self.from start_date
    (Time.at(start_date.to_time.to_i + (10**9))).to_date
  end

end
