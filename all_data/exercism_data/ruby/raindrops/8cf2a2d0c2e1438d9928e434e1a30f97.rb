class Raindrops
  PRIMES = {Pling: 3, Plang: 5, Plong: 7}
  def self.convert(number)
    noise_string = ""
    if number % 3 == 0 || number % 5 == 0 || number % 7 == 0 
      PRIMES.each {|noise, prime| noise_string += noise.to_s if number % prime == 0} 
      noise_string
    else
      number.to_s
    end
  end
end
