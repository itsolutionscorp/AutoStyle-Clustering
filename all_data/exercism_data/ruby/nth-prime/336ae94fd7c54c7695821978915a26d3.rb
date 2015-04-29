class Prime

  def self.nth(num)

    unless num > 0
      raise ArgumentError.new("Only positive integers are allowed")
    end

    i = 2
    counter = 0

    while counter < num

      prime = true
      div = 2

      while div <= (i ** 0.5)
        if i % div == 0
          prime = false
          break
        else
          div += 1
        end
      end

      if prime
        nth_prime = i
        counter += 1
      end
      i += 1

    end

    return nth_prime

  end

end
