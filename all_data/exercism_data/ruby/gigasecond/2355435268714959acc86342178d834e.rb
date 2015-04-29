require 'date'
class Gigasecond

your_birthday =Date.new(1985,8,17)

def self.from (date)
gs = date + (10**9)/(60*60*24)
end

end
