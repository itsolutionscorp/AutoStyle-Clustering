class Sieve
  attr_accessor :num_list, :prime_list

  def initialize(max)
    @num_list = (2..max).to_a
    @prime_list = []
  end

  def primes
    until num_list.empty?
      prime = num_list.shift
      prime_list << prime

      num_list.delete_if { |num| num % prime == 0 }
    end

    prime_list
  end
end
