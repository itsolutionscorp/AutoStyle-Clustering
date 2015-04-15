class Gigasecond
  class << self
    def from(date)
      require 'date'
      (date.to_time + 10**9).to_date
    end
  end
end
