class Raindrops

  PRIME_FACTORS = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(num)
    full_response = PRIME_FACTORS.inject("") { |response, (factor, part)|
      num % factor == 0 ?  response + part : response
    }
    full_response.empty? ? num.to_s : full_response
  end
end
