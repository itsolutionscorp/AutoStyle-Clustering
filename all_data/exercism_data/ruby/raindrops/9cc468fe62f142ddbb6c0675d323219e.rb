require 'prime'

class Raindrops

  def self.convert(num)
    array = []
    if Prime.prime_division(num).flatten.include?(3)
      array << 'Pling'
    end
    if Prime.prime_division(num).flatten.include?(5)
      array << 'Plang'
    end
    if Prime.prime_division(num).flatten.include?(7)
      array << 'Plong'
    end
    if array.empty?
      array << num.to_s
    end
    array.join
  end
end
