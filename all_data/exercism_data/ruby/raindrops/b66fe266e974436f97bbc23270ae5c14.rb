require 'prime'

class Raindrops
  RAINDROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(x)
    result = ""
    RAINDROPS.each do |prime, sound|
      if x.modulo(prime).zero?
        result += sound
      end
    end
    result.empty? ? x.to_s : result
  end

end
