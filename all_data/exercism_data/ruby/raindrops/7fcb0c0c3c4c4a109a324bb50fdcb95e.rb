class Raindrops
  FACTOR_NOISES = [
    [3, "Pling"],
    [5, "Plang"],
    [7, "Plong"]
  ]

  def self.convert(n)
    translation = FACTOR_NOISES.each_with_object("") do |(factor, noise), partial_translation|
      if n % factor == 0
        partial_translation << noise
      end
    end

    if translation.empty? then n.to_s else translation end
  end

end
