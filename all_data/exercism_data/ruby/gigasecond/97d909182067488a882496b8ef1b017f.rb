require 'date'
require 'time'

class Gigasecond
  def self.from(birthday)
    gs = birthday.strftime("%s").to_i + 1000000000
    Date.strptime(gs.to_s, '%s')
  end
end
