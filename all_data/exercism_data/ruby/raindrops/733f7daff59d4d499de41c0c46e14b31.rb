class Raindrops
  DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(num)
    noise = DROPS.inject("") { |acc, (prime, sound)|
      acc += (num % prime == 0 ? sound : "")
    }
    noise.empty? ? num.to_s : noise
  end
end
