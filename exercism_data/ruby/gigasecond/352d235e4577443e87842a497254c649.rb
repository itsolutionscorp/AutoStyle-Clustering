class Gigasecond
  class << self
    def from(t)
      return nil if t.class != Time
      t + (10**9)
    end
  end
end
