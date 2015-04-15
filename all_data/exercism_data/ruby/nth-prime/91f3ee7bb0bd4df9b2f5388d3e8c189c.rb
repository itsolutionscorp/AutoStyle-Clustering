class Prime
  def self.nth(arg)
    if arg <= 0
      raise ArgumentError
    end

    num = 3
    div = 2
    primes = [2]

    while primes.length < arg
      while div < num
        if num % div == 0
          break
        end

        if div == num - 1
          primes << num
          break
        end

        div += 1
      end
      num += 1
      div = 2
    end

    primes[arg - 1]
  end
end
