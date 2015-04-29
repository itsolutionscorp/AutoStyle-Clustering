require 'time'
require 'date'

module Gigasecond
  def Gigasecond.from (date)
    t = Time.parse(date.to_s)
    gs_offset = 10**9
    t += gs_offset
    date = Date.parse(t.to_s)
  end
end
