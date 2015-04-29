require 'prime'

class Raindrops
  def self.convert(number)
    if number == 1
      number.to_s
    else
      factors ||= number.prime_division.flatten.uniq.sort.select do |factor|
        factor > 1 && (factor != number || factor == 3 || factor == 5 || factor == 7)
      end

      string = factors.collect do |factor|
        case factor
        when 3
          'Pling'
        when 5
          'Plang'
        when 7
          'Plong'
        end
      end
      if string.compact.length > 0
        string.join
      else
        number.to_s
      end
    end
  end
end
