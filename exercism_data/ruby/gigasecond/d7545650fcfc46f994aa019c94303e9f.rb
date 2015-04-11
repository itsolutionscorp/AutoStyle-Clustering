class Gigasecond
  class << self

    def from(date)
      days = 10**9 / 60 / 60 / 24
      date + days
    end

  end
end
