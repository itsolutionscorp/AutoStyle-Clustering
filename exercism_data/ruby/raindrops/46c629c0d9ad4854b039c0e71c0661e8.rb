require 'prime'

class Raindrops
  SOUNDS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(num)
    output = ""

    num.prime_division.flatten.uniq.each { |prime| output << SOUNDS[prime] unless SOUNDS[prime].nil? }

    return (output.length > 0 ? output : num.to_s)
  end
end
