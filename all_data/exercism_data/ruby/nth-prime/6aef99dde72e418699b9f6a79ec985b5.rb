class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    primes = []
    current_num = 2
    while primes.length < n
      primes << current_num if is_prime?(current_num) 
      current_num += 1
    end

    primes.last
  end

  private

    def self.is_prime?(n)
      (2..n**0.5).each do |divisor|
        return false if n % divisor == 0
      end
      true
    end
end
