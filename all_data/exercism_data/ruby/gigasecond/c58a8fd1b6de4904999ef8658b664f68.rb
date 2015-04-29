require 'active_support/time'
require 'date'
class Gigasecond
  def self.from(date)
    self.add(date).to_date
  end
  def self.add(date)
    date + (10**9).seconds
  end
end
