class Sieve

  attr_reader :primes_up_to, :prime_array
  def initialize(primes_up_to)
    @primes_up_to = primes_up_to
  end

  def primes
    @prime_array ||= generate_prime_list
  end

  private
    def generate_prime_list
      prime_array = *(2..primes_up_to)
      count = 0
      while count < prime_array.length
        value = prime_array[count]
        (count..prime_array.length-1).step(value) { |i| prime_array[i] = nil unless i==count } unless value.nil?
        count += 1
      end
      prime_array.compact!
    end

end
