require 'time'

module Gigasecond

  def Gigasecond.from(date)
    d = date.to_time.to_i + (10**9)
    return Time.at(d).to_date
  end

end
