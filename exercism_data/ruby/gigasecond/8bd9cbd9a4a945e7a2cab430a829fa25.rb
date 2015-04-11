class Gigasecond
  def self.from(t)
    t.to_i
    t += 10**9
    Time.at(t)
  end
end
