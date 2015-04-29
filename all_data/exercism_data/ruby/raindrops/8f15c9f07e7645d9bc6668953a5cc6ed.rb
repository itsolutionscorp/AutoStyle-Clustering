class Raindrops
  def self.convert(number)
    raindrops = ""
    primes = {3 => "Pling", 5 => "Plang", 7=> "Plong"}
    primes.each do |k,v|
      raindrops += v if number % k == 0
    end
    raindrops == "" ? number.to_s : raindrops
  end
end
