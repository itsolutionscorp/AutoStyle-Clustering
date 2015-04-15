require 'prime'

class Raindrops
  def self.convert(n)
    filter = [3, 5, 7]

    s = Prime.prime_division(n).flatten.uniq.map { |p|
      case p
      when 3
        "Pling"
      when 5
        "Plang"
      when 7
        "Plong"
      end
    }.join("")

    s.empty? ? n.to_s : s
  end
end
