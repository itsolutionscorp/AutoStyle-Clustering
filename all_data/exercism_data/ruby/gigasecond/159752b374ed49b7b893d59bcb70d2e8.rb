require 'date'
require 'time'
module Gigasecond
  # @param [Date] date
  # @return [Date] date + gigasecond
  def self.from(date)
    giga_birthday = Time.new(date.year, date.month, date.day)
    giga_birthday += (10**9)
    giga_birthday.to_date
  end
end


