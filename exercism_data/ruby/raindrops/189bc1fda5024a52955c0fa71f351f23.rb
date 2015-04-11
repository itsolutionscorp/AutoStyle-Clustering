class Raindrops

  REPLACEMENTS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    prime_factors = prime_factors(number).uniq

    if (contains_replacements(prime_factors))
      prime_factors.map { |p| REPLACEMENTS[p] || '' }.join
    else
      return number.to_s
    end
  end

  private

  def self.prime_factors(number)
    return [number] if number < 2

    prime_factors = []

    loop do
      prime_factor = next_prime_factor(number)
      prime_factors << prime_factor

      # stop if number is a prime itself
      break if (prime_factor == number)

      number /= prime_factor
    end

    prime_factors
  end

  def self.next_prime_factor(number)
    (2..number).find { |i| (number % i) == 0 }
  end

  def self.contains_replacements(array)
    return array.any? { |i| REPLACEMENTS.keys.include?(i) }
  end
end
