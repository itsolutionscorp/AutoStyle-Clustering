class Raindrops
  def self.convert(n)
    str = ""
    str << "Pling" if n % 3 == 0
    str << "Plang" if n % 5 == 0
    str << "Plong" if n % 7 == 0
    str.empty? ? n.to_s : str
  end
end
