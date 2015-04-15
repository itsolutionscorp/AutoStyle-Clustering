class Prime

  def self.nth(n)
    raise ArgumentError, 'Argument should not be zero' unless n >= 1
#    generate_primes(n*11)[n-1]
    sieve(n*11)[n-1]
  end

  def self.sieve(max=100)
    sieve = []
    (2..max).each { |i| sieve[i] = i }
    (2..Math.sqrt(max)).each do |i|
      (i*i).step(max, i) { |j| sieve[j] = nil } if sieve[i]
    end
    sieve.compact
  end

  def self.generate_primes(max=2)
    number_list = (2..max).map { |i| i }
    number_list.each do |i|
      unless i == nil
        (2..max/3).each do |j|
          if i*j <= number_list.length
            number_list[i*j-2] = nil
          end
        end
      end
    end
    number_list.compact
  end

end
