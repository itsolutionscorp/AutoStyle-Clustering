class Gigasecond
  GIGASECOND = 10**9

  def self.from(time)
    Time.at(time.to_i + GIGASECOND)
  end
end
