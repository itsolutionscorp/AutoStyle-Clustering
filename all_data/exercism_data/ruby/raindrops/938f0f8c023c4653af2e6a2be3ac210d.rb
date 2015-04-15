class Raindrops

  def self.convert drops
    sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    sounds.select { |num, sound| drops % num == 0 }.values.join[/P.*/] || drops.to_s
  end

end
