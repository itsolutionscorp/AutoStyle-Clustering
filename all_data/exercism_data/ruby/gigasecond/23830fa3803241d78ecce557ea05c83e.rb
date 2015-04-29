class Gigasecond
  class << self
    def one_billion_seconds
      1000000000
    end

    def from(date)
      (date.to_time+one_billion_seconds).to_date
    end
  end
end
