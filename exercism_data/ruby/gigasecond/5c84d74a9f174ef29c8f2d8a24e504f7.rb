require 'date'

class Gigasecond
  ONE = 10**9

  class << self
    def from(date)
      increment_date(date)
    end

    def increment_date(date)
      (date.to_time + ONE).to_date
    end
  end
end
