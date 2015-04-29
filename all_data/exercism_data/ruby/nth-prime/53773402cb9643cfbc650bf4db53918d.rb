class Prime
  def self.nth(arg)
    if arg <= 0
      raise ArgumentError
    end

    count = 1
    num = 3
    div = 2
    prime = 2

    while count < arg
      while div < num
        if num % div == 0
          break
        end

        if div == num - 1
          prime = num
          count += 1
          break
        end

        div += 1
      end
      num += 1
      div = 2
    end

    prime
  end
end
