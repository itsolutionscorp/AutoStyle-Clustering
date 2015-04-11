class Gigasecond

  class << self
    def seconds
      Rational(10**9)
    end

    def seconds_in_day
      60 * 60 * 24
    end

    def days
      seconds / seconds_in_day
    end

    def from(date)
      (date.to_datetime + days).to_date
    end
  end

end
