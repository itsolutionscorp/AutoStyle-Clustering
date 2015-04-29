class Gigasecond

  TIME_GIGASECOND = 10**9

  def self.from(t)
    t.to_i
    t += TIME_GIGASECOND
    Time.at(t)
  end
end
