require 'prime'
class Raindrops
  class << self
    def convert(number)
      return '1' if number.eql? 1
      str = ''
      number.prime_division.each do |prime, value|
        case prime
        when 3
          str << 'Pling'
        when 5
          str << 'Plang'
        when 7
          str << 'Plong'
        end
      end
      str.include?('P') ? str : number.to_s
    end
  end
end
