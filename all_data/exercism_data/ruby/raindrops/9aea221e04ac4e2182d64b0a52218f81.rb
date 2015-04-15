class Raindrops

  def self.convert(n)
    raindrop_speak = check_3(n) + check_5(n) + check_7(n)

    raindrop_speak != "" ? raindrop_speak : n.to_s
  end

  def self.check_3(n)
    n % 3 == 0 ? "Pling" : ""
  end

  def self.check_5(n)
    n % 5 == 0 ? "Plang" : ""
  end

  def self.check_7(n)
    n % 7 == 0 ? "Plong" : ""
  end

end
