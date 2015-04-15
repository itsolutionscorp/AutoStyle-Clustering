require 'prime'

class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  def self.convert(num)
    factors = Prime.prime_division(num).flatten
    result = []
    return num.to_s if num == 1 || (factors & SOUNDS.keys).empty?
    # return num.to_s if (factors & SOUNDS.keys).empty?
    factors.each do |factor|
      result << SOUNDS[factor]
    end
    result.join('')
  end
end

p Raindrops.convert(25)
