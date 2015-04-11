module Gigasecond
  require 'date'
  def self.to_seconds
    10**9
  end

  def self.to_time
    Time.new(self.to_seconds)
  end

  def self.from_date(date)
    (date.to_time + self.to_seconds).to_date
  end

  def self.from(date)
    raise "Argument should be a date" unless date.is_a? Date
    return self.from_date(date)
  end
end
