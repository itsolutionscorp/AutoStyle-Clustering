class Prime

  class << self
    # naive implementation of finding primes 
    def nth(number)
      raise ArgumentError if number < 1
      return 2 if number == 1
      primes_list = [2]
      trial_number = 3
      until primes_list.length >= number do
        primes_list << trial_number if is_prime?(trial_number, primes_list)
        trial_number += 2
      end
      primes_list[number-1]
    end

    private
      def is_prime?(number, primes_list = nil)
        primes_list ||= (3..Math.sqrt(number).to_i).step(2)
        primes_list.take(Math.sqrt(number).to_i).each do |prime|
          return false if number % prime == 0
        end
      end
  end

end
