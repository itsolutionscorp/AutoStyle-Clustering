class Raindrops
  def self.convert(n)
     [[n%3, n%5, n%7].map(&:zero?), ["Pling", "Plang", "Plong"]].transpose.map{ |x,y| y if x }.compact.reduce(:concat) || n.to_s
  end
end
