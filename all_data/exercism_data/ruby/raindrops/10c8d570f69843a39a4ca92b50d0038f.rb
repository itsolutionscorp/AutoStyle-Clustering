require 'prime'

class Raindrops
  @rain_decoder = { "Pling" => 3, "Plang" => 5, "Plong" => 7 }

  def self.convert(count)
    primes = count.prime_division.transpose.first
    raindrop_speak_for(primes)
  rescue
    count.to_s
  end

private

  def self.raindrop_speak_for(primes)
    raindrop_speak = ""
     primes.each do |prime|
      @rain_decoder.each do |rain_lang, value|
        raindrop_speak << rain_lang if prime == value
      end
    end
    result_from(raindrop_speak)
  end

  def self.result_from(raindrop_speak)
   !raindrop_speak.empty? ? raindrop_speak : count.to_s
  end

end
