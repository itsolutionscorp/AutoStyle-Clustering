class Raindrops
  PRIMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  class << self
    def convert(number)
      rain_number = PRIMES.inject('') { |str, (key,value) | number%key==0 ? str + value : str }
      rain_number.empty? ? number.to_s : rain_number
    end
  end
end
