class Raindrops
  @sound_of_drops = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(input_number)
    output = []
    @sound_of_drops.each do |number, sound|
      output << sound if (input_number % number == 0)
    end
    output << input_number if output.empty?
    return output.join
  end

end
0
