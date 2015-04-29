class Raindrops
  def self.convert(n)
    {3 => 'Pling', 5 => "Plang", 7 => "Plong"}
         .select {|factor, string| n % factor == 0}
         .values
         .reduce(:concat) || n.to_s
  end
end
