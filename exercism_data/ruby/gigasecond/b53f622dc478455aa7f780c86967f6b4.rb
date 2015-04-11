class Gigasecond
  def self.from start_at
    Time.at(start_at.to_i + 10**9)
  end
end
