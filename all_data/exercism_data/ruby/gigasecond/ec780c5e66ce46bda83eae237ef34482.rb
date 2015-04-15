class Gigasecond
 require 'pry'
  def self.from(date)
    date + gigasecond_in_days
  end

  class << self
    def gigasecond_in_days
      gigasecond/day_in_seconds
    end

    def day_in_seconds
      24*60*60
    end

    def gigasecond
      10**9
    end
  end

end
