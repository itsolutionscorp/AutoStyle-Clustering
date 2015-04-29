class Raindrops

  def self.convert(input)
    output = ""
    sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    sounds.each do |count, sound|
      output += sound if input % count == 0
    end
    output == "" ? input.to_s : output
  end

end
