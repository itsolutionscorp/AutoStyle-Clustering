class Gigasecond

  def self.from(start)
    Time.at(start.to_time.to_i + 10**9).to_date
  end

end
