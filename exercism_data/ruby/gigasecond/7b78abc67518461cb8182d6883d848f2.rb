class Gigasecond
  def self.from(arg1)
    (arg1.to_time + 10**9).to_date
  end
end
