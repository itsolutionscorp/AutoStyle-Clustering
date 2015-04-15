require 'active_support/core_ext'

class Gigasecond
  def self.from(date)
    date += (10**9).seconds
    Date.parse(date.to_s)
  end
end
