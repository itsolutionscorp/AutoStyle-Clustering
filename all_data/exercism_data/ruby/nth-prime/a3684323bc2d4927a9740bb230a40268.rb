class Prime
  def self.nth(num)
    raise ArgumentError if num < 1
    primes = []
    counter = 2
    while primes.length < num
      primes << counter unless (2..Math.sqrt(counter)).map { |el| counter % el == 0 }.include? true
      counter += 1
    end
    primes.last
  end
end
