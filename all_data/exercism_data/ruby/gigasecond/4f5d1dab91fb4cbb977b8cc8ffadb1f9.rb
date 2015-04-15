class Gigasecond
  class << self
    def from(date)
      date + (10**9)/24/60/60
    end
  end
end
