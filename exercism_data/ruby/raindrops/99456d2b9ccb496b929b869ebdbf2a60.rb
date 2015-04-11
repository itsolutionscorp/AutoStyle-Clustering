class Raindrops
  require 'mathn'
  RAINDROPS = {3 => 'Pling', 5 => "Plang", 7 =>"Plong"}

  def self.convert(number)

    primes = Prime.prime_division(number)
    rain = String.new()

    primes.each do |p|
        rain += RAINDROPS[p[0]].to_s
    end

    return rain.empty? ? number.to_s : rain

  end

end
