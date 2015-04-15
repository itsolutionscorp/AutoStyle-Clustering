class Sieve
  def initialize(number)
    @list = number
  end

  def primes
    (2...@list).each_with_object((2..@list).to_a) do |current_number, sieve|
      sieve.reject! { |element| element != current_number && (element % current_number == 0) }
    end
  end
end
