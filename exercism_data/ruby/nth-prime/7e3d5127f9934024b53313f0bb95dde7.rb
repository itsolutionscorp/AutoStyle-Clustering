class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    primes = []
    i = 2
    while primes.count <= n - 1
      primes << i if prime? i
      i += 1
    end
    primes.last
  end

  private

    def self.prime?(number)
      return true if [1, 2].include? number
      range = 2.upto(Math.sqrt(number).ceil).to_a
      i = 0
      while i < range.count
        return false if number % range[i] == 0
        range = range.reject { |j| j % range[i] == 0 }
      end
      true
    end
end
