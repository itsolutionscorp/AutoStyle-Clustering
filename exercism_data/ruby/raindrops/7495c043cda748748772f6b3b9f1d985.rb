class Raindrops

  @primes = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    rain_drops = @primes.reduce('') do | memo, (prime, rain_drop) |
      memo += (number % prime == 0) ? rain_drop : ''
    end 

    rain_drops = rain_drops.empty? ? number.to_s : rain_drops
  end

end
