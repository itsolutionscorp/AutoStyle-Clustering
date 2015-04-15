class Raindrops

  # - If the number contains 3 as a prime factor, output 'Pling'.
  # - If the number contains 5 as a prime factor, output 'Plang'.
  # - If the number contains 7 as a prime factor, output 'Plong'.
  # - If the number does not contain 3, 5, or 7 as a prime factor,
  #   just pass the number's digits straight through.

  FACTORS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }


  def self.convert(raindrop)
    output_string = ""

    FACTORS.each do |factor, word|
      output_string += word if raindrop % factor == 0
    end

    if output_string.empty?
      return raindrop.to_s
    else
      return output_string
    end
  end
end
