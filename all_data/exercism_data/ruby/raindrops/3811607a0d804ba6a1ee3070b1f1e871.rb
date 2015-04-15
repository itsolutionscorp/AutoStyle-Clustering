class Raindrops
  def self.convert(n)
    string = ""
    string << "Pling" if n % 3 == 0
    string << "Plang" if n % 5 == 0
    string << "Plong" if n % 7 == 0
    string.empty? ? n.to_s : string
  end
end
