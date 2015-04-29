require 'prime'

class Prime
  class << self
    def nth(n)
      n < 1 and fail ArgumentError
      enough_primes(n)[n]
    end

  private

    def enough_primes(n)
      each(upper_bound(n)).reduce([nil], :push)
    end

    def upper_bound(n)
      return 2 * n + 1 if n < 6
      n * Math.log(n) + n * Math.log(Math.log(n))
    end
  end
end
