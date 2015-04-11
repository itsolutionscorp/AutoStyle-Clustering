class Gigasecond

  def self.from(date)
    Time.at(date.to_time + 10**9).to_date
  end

end
