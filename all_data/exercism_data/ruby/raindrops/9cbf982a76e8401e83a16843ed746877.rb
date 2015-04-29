class Raindrops
  SOUND_VALS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(input)
    output = SOUND_VALS.map { |key, value| sound(input, key) }.join

    output.empty? ? input.to_s : output
  end

  private
  def self.sound(input, val)
    input % val == 0 ? SOUND_VALS[val] : ""
  end

end
