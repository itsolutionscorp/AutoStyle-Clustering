class Raindrops

  SOUNDS = {3 => 'Pling', 5=> 'Plang', 7 => 'Plong'}

  def self.convert(number)
    string = SOUNDS.select {|key, sound| number%key == 0}.values.join
    string.empty? ? number.to_s : string
  end
end
