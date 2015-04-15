require "prime"

class PrimeFactors
  def self.for(number)
    # if number == 1
    #   []
    # # elsif Prime.prime?(number)
    # #   [number]
    # else
    #   factors(number, [])
    # end
    candidate = 2
    primes = []
    while number > 1
      while number % candidate == 0
        primes << candidate
        number /= candidate
      end
      candidate += 1
    end
    primes
  end

  private

  # factors(6,[])
  #    primes [2,3]
  #    iter#1 6%2
  #      factors(3,[2])
  #      primes [3]
  #      iter#1a 3%3
  #      factors(1, [2, 3])
  #    iter#2 6%3
  #      factors(2, [2,3])
  #      primes [2]
  #      iter#1a 2%2
  #      factors(1, [2, 3, 3, 2])
  # def self.factors(number, array)
  #   Prime.each(number).reverse_each do |prime|
  #     # break if prime == number
  #     if number%prime == 0
  #       array << prime
  #     end

  #   end

  #   while (product = product_of(array)) < number
  #     factors(number/product, array)
  #   end

  #   array.sort
  # end

  # def self.product_of(array)
  #   array.inject(1) {|p,n| p*n}
  # end
end
