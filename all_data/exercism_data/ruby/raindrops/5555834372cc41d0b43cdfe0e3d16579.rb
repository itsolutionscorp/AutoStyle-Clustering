class Raindrops
  def self.convert(input)
    output = ""
    output += pling(input)
    output += plang(input)
    output += plong(input)
    output == "" ? input.to_s : output
  end

  def self.pling(input)
    input % 3 == 0 ? "Pling" : ""
  end

  def self.plang(input)
    input % 5 == 0 ? "Plang" : ""
  end

  def self.plong(input)
    input % 7 == 0 ? "Plong" : ""
  end

end
