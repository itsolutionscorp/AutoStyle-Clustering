require 'date'
require 'time'
module Gigasecond

  private
  GIGASECOND = 10**9

  public
  # @param [Date] date
  # @return [Date] date + gigasecond
  def self.from(date)
    giga_birthday = Time.new(date.year, date.month, date.day)
    giga_birthday += GIGASECOND
    giga_birthday.to_date
  end
end


