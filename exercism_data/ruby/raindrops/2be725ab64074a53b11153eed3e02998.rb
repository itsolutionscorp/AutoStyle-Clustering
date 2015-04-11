class Raindrops
  def self.convert(string)
    number = string.to_i
    raindrops = ""
    primes = {"Pling" => 3, "Plang" => 5, "Plong" => 7}
    primes.each do |k,n|
      raindrops += k if number % n == 0
    end
    raindrops == "" ? number.to_s : raindrops
  end
end
