require 'prime'

class Raindrops
  def self.convert number
    out = ""
    [3, 5, 7].each do |num|
      out << gen_tone(num) if number % num == 0
    end
    out.empty? ? number.to_s : out
  end

  def self.gen_tone num
    case num
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
