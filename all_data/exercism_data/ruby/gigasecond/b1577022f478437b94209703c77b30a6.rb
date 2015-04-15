class Gigasecond
  BILLION = 10 ** 9

  class << self
    def from(time)
      time + Gigasecond::BILLION
    end
  end

end
