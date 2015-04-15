class Raindrops

  def initialize
    @factor_output_map = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end

  def convert(incoming_number)
    phrase = @factor_output_map.inject("") do | output, (factor, word) |
      output += word if incoming_number % factor == 0
      output
    end
    phrase = incoming_number.to_s if phrase.empty?
    phrase
  end

end
