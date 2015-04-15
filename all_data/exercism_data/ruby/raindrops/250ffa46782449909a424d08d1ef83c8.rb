class Raindrops

  def self.convert n
    raindrop_speak = ""
    found_factors = self.factors(n)
    raindrop_speak += "Pling" if found_factors.include? 3
    raindrop_speak += "Plang" if found_factors.include? 5
    raindrop_speak += "Plong" if found_factors.include? 7
    raindrop_speak = n.to_s if raindrop_speak.empty?
    raindrop_speak
  end

  def self.factors n
    factors = []
    d = 2
    while n > 1 do
      while (n % d == 0) do
        factors << d
        n /= d
      end
      d = d+1
    end

    factors
  end
end
