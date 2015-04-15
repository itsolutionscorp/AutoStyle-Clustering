class Raindrops
  def self.convert(n)
    output = ""

    output << "Pling" if n.divisible_by?(3)
    output << "Plang" if n.divisible_by?(5)
    output << "Plong" if n.divisible_by?(7)

    output.present? ? output : n.to_s
  end

end

class Integer
  def divisible_by?(n)
    self % n == 0
  end
end
