require 'date'

class Gigasecond
  def self.from(date)
    new(date).gs
  end

  def initialize(date)
    @date = date
  end

  def gs
    Time.at(unix_timestamp + gigasecond).to_date
  end

  private
  attr_reader :date

  def unix_timestamp
    date.to_time.to_i
  end

  def gigasecond
    1_000_000_000
  end
end
