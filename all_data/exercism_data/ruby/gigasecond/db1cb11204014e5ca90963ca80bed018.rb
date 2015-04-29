require 'date'
require 'time'

class Gigasecond
  def self.from(birthday)
    gs = birthday.strftime("%s").to_i + 10**9
    Date.strptime(gs.to_s, '%s')
  end
end
