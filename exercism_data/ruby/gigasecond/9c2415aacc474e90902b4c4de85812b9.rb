class Gigasecond

  def self.from(some_time)
     Time.at(some_time.to_i+10**9)
  end
end
