class Gigasecond

  def self.from(date)
    ds = date.to_time.to_i + 10**9
    Time.at(ds).to_date
  end

end
