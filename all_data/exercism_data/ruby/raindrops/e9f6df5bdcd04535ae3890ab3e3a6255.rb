require 'prime'

class Raindrops
  def self.convert(num)
    msg = ""
    num.prime_division.flatten.each do |testNum|
      case testNum
        when 3
          msg << 'Pling'
        when 5
          msg << 'Plang'
        when 7
          msg << 'Plong'
      end
    end
    msg.empty?? num.to_s : msg
  end
end
