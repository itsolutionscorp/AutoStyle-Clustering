require 'pry'
class Gigasecond

  def self.from(date)
    days_in_gigasecond = 1000000000/(60 * 60 * 24)
    gs = date + days_in_gigasecond
  end

end
