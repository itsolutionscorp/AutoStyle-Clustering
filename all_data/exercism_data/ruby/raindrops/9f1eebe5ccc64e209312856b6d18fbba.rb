require 'prime'

class Raindrops
  def self.convert(num)
    raindrops = Prime.prime_division(num).flatten.uniq.map { |factor|
      case factor
      when 3 then "Pling"
      when 5 then "Plang"
      when 7 then "Plong"
      else ""
      end
    }.join
    raindrops.empty? ? num.to_s : raindrops
  end
end
