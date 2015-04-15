class Raindrops

  def self.primes
    [3, 5, 7]
  end

  def self.has_prime_factor?(number)
    primes.each { |prime| return false if number % prime == 0 }
  end

  def self.convert(number)
    return number.to_s if has_prime_factor?(number)
    @temp_string = ''

    rain(number)
  end

  def self.rain(number)
    if number % 3 == 0
      number /= 3
      @temp_string << 'Pling'
      rain(number)
    elsif number % 5 == 0
      number /= 5
      @temp_string << 'Plang'
      rain(number)
    elsif number % 7 == 0
      number /= 7
      @temp_string << 'Plong'
      rain(number)
    end
    @temp_string
  end
end
