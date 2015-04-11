class Gigasecond
  class << self
    def from(date)
      increment_date(date)
    end

    def increment_date(date)
      require 'date'
      (date.to_time + one).to_date
    end

    def one
      10**9
    end
  end
end
