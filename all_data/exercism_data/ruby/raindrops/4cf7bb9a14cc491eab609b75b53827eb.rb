class Raindrops
  DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert number
    drops = [3, 5, 7].map { |type| 
      number % type == 0 ? DROPS[type] : ""
    }.reduce(:+)

    drops.empty?  ? number.to_s : drops
  end
end
