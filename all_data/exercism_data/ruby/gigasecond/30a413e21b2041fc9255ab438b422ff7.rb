require 'active_support/time'

class Gigasecond
  def self.from(date)
    new_date = date + (10 ** 9).seconds
    Date.new(new_date.year, new_date.month, new_date.day)
  end
end
