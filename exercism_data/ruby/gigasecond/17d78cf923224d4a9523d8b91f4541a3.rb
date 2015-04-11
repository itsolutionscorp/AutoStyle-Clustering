module Gigasecond
  require 'active_support/time'
  extend self
  
  def from(date)
    (date + (10**9).seconds).to_date
  end
end
