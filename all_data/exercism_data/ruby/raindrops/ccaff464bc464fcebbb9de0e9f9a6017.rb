class Raindrops
  def self.convert(n)
    x = {3 => 'Pling', 5 => "Plang", 7 => "Plong"}
         .select {|factor, string| n % factor == 0}
         .values
         .join
    x.empty? ? n.to_s : x
  end
end
