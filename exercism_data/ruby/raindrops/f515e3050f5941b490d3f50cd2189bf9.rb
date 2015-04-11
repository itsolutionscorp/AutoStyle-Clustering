require 'prime'

class Raindrops
  @rain_decoder = { "Pling" => 3, "Plang" => 5, "Plong" => 7 }

  def self.convert(count)
    primes = count.prime_division.transpose.first
    raindrop_sound_for(primes)
  rescue
    count.to_s
  end

private

  def self.raindrop_sound_for(primes)
    rain_dropping = ""
     primes.each do |prime|
      @rain_decoder.each do |drop, value|
        rain_dropping << drop if prime == value
      end
    end
    result_from(rain_dropping)
  end

  def self.result_from(rain_dropping)
   !rain_dropping.empty? ? rain_dropping : count.to_s
  end

end
