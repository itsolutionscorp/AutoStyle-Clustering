class Sieve
  def initialize(max)
    @max = max
  end

  def primes
    potentials = (2..@max).to_a
    return_value = []
    while potentials.any?
      return_value << (potential = potentials.shift)
      potentials.reject! { |e| e % potential == 0 }
    end
    return_value
  end

end
