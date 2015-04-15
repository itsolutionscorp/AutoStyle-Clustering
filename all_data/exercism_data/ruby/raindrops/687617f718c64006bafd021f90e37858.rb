class Raindrops

  @primes_to_rain = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  @present = {
    2 => false,
    3 => false,
    5 => false,
    7 => false
  }

  def self.check_primes_presence(x)
    @present.each_key { |i| @present[i] = false }
    @present.each_key do |prime|
      while x % prime == 0 do
        @present[prime] = true
        x /= prime
      end
    end
    @present.select { |i| i > 2 }.has_value?(true)
  end

  def self.convert(in_value)
    prime_factors_exist = check_primes_presence(in_value)
    output = ""
    @primes_to_rain.select { |i| @present[i] }.each_value { |j| output << j }
    if prime_factors_exist
      output
    else
      in_value.to_s
    end

  end
end
