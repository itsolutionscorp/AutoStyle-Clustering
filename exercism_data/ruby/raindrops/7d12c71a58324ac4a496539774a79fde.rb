class Raindrops
  def self.convert(x)
    result = []
    result << "Pling" if  x % 3 == 0 
    result << "Plang" if  x % 5 == 0 
    result << "Plong" if  x % 7 == 0
    result << x       if result == []
    result.join
  end
end
