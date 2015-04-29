class Gigasecond

  def self.from(start_time)
    Time.at(start_time.to_i + 10**9)
  end

end
