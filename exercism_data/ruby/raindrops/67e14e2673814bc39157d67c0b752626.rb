require 'prime'

class Raindrops

  def self.convert(x)
    divs = x.prime_division.flatten.uniq.sort

    a = divs.map do |x|
      if x == 3
        "Pling"
      elsif x == 5
        "Plang"
      elsif x == 7
        "Plong"
      else
        nil
      end
    end.compact

    a.empty? ? x.to_s : a.join("")
  end

end
