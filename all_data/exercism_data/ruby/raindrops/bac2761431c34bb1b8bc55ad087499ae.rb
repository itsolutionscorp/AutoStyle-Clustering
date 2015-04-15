class Raindrops
  def self.convert(num)
    str = "#{pling(num)}#{plang(num)}#{plong(num)}"
    str.empty? ? num.to_s : str
  end

  def self.pling(num)
    "Pling" if num % 3 == 0
  end

  def self.plang(num)
    "Plang" if num % 5 == 0
  end

  def self.plong(num)
    "Plong" if num % 7 == 0
  end
end
