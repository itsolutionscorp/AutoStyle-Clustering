require 'date'

class Gigasecond

  def initialize(start_date)
    @init_date = start_date
  end

  def self.from(start_date)
    Gigasecond.new(start_date)
  end

  def date
    (@init_date.to_time + 10**9).to_date
  end
end
