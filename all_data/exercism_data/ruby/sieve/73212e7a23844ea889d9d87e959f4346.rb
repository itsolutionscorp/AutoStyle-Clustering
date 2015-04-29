class Sieve
  attr_reader :num
  def initialize(num)
    @num = num
  end

  def primes
    primes  = []
    stop    = Math.sqrt(@num)
    working = (2..@num).to_a
    begin
      seek = working.shift
      primes << seek
      working.select! { |n| n % seek != 0 }
    end while working.size > 0
    primes + working
  end
end
