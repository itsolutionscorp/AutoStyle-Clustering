class Raindrops

  def self.primes
    [3, 5, 7]
  end

  def self.temp_string
    @temp_string
  end

  def self.has_no_prime_factor?(number)
    primes.each { |prime| return false if number % prime == 0 }
  end


  def self.convert(number)
    return number.to_s if has_no_prime_factor?(number)
    @temp_string = ''

    make_it_rain(number)
  end

  def self.data
    { 3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end


  def self.make_it_rain(number)

    data.each do |prime, string|

      if number % prime == 0

        number /= prime
        temp_string << string

        make_it_rain(number) unless has_no_prime_factor?(number)

        return temp_string
      end
    end
  end
end
