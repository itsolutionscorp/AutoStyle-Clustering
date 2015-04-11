###
# Raindrops
# Clay Diffrient
###

require 'prime'

class Raindrops

  def self.convert(value)
    result = []
    factors = Prime.prime_division(value)
    factors.each do |factor|
      case factor[0]
      when 3
        unless result.include?("Pling")
          result << "Pling"
        end
      when 5
        unless result.include?("Plang")
          result << "Plang"
        end
      when 7
        unless result.include?("Plong")
          result << "Plong"
        end
      end
    end
    if result.count == 0
      result << value
    end
    result.join ""
  end

end
