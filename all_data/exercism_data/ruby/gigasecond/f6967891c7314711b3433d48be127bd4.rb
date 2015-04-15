class Gigasecond
  class << self
    def from(time = Time.now)
      time + 1000000000
    end
  end
end
