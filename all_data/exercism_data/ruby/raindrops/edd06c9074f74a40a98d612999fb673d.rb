class Raindrops

  def self.convert(input_number)
    sound_of_drops = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    output = sound_of_drops.reduce([]) do |output, (number, sound)|
      (input_number % number == 0) ? output << sound : output
    end.join
    output.empty? ? input_number.to_s : output
  end

end
