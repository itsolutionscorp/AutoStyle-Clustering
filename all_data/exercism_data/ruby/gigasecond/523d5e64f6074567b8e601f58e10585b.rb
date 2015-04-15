class Gigasecond
  require 'date'
  require 'time'

  def self.from(fromdate)
    gigadate = Date.parse((Time.parse(fromdate.to_s) + 10**9).to_s)
    return gigadate
  end

end
