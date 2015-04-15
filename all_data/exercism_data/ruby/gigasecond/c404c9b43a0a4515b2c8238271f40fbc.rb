class Gigasecond
  class << self
    def from(time)
      Time.at(time.to_i + (10**9))
    end
  end
end
