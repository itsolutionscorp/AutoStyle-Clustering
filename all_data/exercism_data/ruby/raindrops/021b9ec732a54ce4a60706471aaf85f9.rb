class Raindrops

  # - If the number contains 3 as a prime factor, output 'Pling'.
  # - If the number contains 5 as a prime factor, output 'Plang'.
  # - If the number contains 7 as a prime factor, output 'Plong'.
  # - If the number does not contain 3, 5, or 7 as a prime factor,
  #   just pass the number's digits straight through.


  def self.convert(raindrop)
    string = ""
    if raindrop % 3 == 0
      string += "Pling"
    end

    if raindrop % 5 == 0
      string += "Plang"
    end

    if raindrop % 7 == 0
      string += "Plong"
    end

    if string.length == 0
      return raindrop.to_s
    else
      return string
    end

  end
end
