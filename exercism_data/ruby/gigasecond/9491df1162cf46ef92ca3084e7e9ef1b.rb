require 'active_support/all'

class Gigasecond
  def self.from(datetime)
    datetime.send("+",(10**9).seconds).to_date
  end
end
