class Raindrops
  def self.convert(n)
     [3, 5, 7].zip(["Pling", "Plang", "Plong"]).map{ |x, y| y if (n%x).zero? }.compact.reduce(:concat) || n.to_s
  end
end
