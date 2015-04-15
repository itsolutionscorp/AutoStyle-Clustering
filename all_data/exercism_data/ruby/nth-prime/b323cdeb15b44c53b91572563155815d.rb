class Prime

  def self.nth(n)
    raise ArgumentError, 'Argument is too low' unless n > 0
    i = 0
    c = 2
    while i < n do

      not_prime = false

      for j in 2..Math.sqrt(c) do

        if (c % j) == 0 then
          not_prime = true
          break
        end

      end

      if !(not_prime) then
        i = i + 1
      end

      c = c + 1

    end

    c-1
  end

  if __file__ = $0
    puts Prime.nth(100)
  end
end
