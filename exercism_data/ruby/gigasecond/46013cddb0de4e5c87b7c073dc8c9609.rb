class Gigasecond
  def self.from(arg)
    Time.at(arg.to_time.to_i + 10**9).to_date
  end
end
