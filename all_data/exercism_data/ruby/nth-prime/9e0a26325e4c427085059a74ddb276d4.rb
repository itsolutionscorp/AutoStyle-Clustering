class Prime

  def self.prime?(num)
    (2..Math.sqrt(num)).each{|divisor| return false if num % divisor == 0 }
    true
  end

  def self.nth(n)
    raise ArgumentError if n < 1

    primes = []
    runner = 2

    while true
      primes << runner if prime?(runner)
      break if primes.size == n
      runner += 1
    end

    primes.last
  end

end
