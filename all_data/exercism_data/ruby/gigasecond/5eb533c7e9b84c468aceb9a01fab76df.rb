require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    gs = 1000000000  
    Date.strptime((date.to_time + gs).to_s, '%Y-%m-%d')
  end  
end
