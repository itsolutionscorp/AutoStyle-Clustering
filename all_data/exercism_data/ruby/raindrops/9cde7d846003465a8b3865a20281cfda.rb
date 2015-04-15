class Raindrops
  PRIMES = {"Pling" => 3, "Plang" => 5, "Plong" => 7}

  def self.convert(string)
    number = string.to_i
    raindrops = PRIMES.select do |k,n|
      k if number % n == 0
    end.keys.join
    raindrops == "" ? number.to_s : raindrops
  end
end
