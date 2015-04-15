class Raindrops

  SNDS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert drops
    SNDS.select { |n, snd| drops % n == 0 }.values.join[/\w.*/] || drops.to_s
  end

end
