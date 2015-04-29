class Gigasecond
  def self.from(t)
    Time.at(t.to_i + 10**9)
  end
end
