class Sieve
  def initialize(upper_limit)
    @upper_limit = upper_limit
    @primes = nil
  end

  def generate_primes(upper_limit)
    range = (2..upper_limit).to_a

    range.each do | number |
      range = remove_multiples(range, number)
    end

    range
  end

  def primes
    return @primes unless @primes.nil?
    @primes = generate_primes(@upper_limit)
  end

  def remove_multiples(initial_array, integer)
    initial_array - generate_multiples(integer, @upper_limit)
  end

  def generate_multiples(integer, upper_limit)
    multiples_array = [(2 * integer)]
    n = 3

    while multiples_array.max < upper_limit
      multiples_array << (n * integer)
      n += 1
    end
    multiples_array
  end
end
