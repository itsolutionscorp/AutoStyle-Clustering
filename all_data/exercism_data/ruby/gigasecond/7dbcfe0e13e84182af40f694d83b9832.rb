require 'date'
require 'time'
class Gigasecond

  def self.from(date)
    # return Date.new(2043,1,1) - Date.new(2011,4,23) 
    if date.class.to_s == "Time"
      return date.to_date + (11575/1)
    else
      return date + (11574/1)
    end
  end

end
