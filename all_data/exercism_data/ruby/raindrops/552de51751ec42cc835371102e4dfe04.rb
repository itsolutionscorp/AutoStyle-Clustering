class Raindrops
  def self.convert(n)
    s = ""
    s += "Pling" if n % 3 == 0
    s += "Plang" if n % 5 == 0
    s += "Plong" if n % 7 == 0
    s == "" ? n.to_s : s
  end
end
