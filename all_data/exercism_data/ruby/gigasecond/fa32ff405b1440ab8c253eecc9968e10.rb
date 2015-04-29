require 'active_support/time'

module Gigasecond
  def self.from(date)
   date + (10**9).seconds
  end
end
