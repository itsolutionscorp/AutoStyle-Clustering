class Raindrops
  FACTOR_NOISES = [
    [3, "Pling"],
    [5, "Plang"],
    [7, "Plong"]
  ]

  def self.convert(n)
    response = ""

    FACTOR_NOISES.each do |factor,noise|
      if n % factor == 0
        response += noise
      end
    end

    if response.empty? then n.to_s else response end
  end
end
