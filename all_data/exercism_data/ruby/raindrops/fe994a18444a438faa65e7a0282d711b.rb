require 'prime'

class Raindrops
  def self.convert num
    out = ""
    num.prime_division.each do |pair|
      out << gen_tone(pair)
    end
    out.empty? ? num.to_s : out
  end

  def self.gen_tone pair
    case pair[0]
    when 3
      "Pling"
    when 5
      "Plang"
    when 7
      "Plong"
    else
      ""
    end
  end
end
