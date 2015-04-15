class Raindrops

  attr_reader :drops, :sounds 

  def self.convert drops
    converter = new(drops).conversion
  end

  def initialize drops
    @drops = drops
    @sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def conversion
    sounds.select { |num, sound| drops % num == 0 }.values.join[/P.*/] || drops.to_s
  end

end
