require 'date'
require 'time'

module Gigasecond
  SECONDS = 10**9

  module_function

  def from(date)
    (date.to_time + SECONDS).to_date
  end
end
