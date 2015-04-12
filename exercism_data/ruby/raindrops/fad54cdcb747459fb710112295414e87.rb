class Raindrops
  def self.convert n
    result = ""
    result << "Pling" if n % 3 == 0
    result << "Plang" if n % 5 == 0
    result << "Plong" if n % 7 == 0
    result << n.to_s if result == ""
    result
  end
end