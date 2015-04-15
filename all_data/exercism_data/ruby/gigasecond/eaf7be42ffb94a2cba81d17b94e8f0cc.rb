class Gigasecond
  class << self
    def from(time)
      time + seconds
    end

    def seconds
      @seconds ||= 1_000_000_000
    end
  end
end
