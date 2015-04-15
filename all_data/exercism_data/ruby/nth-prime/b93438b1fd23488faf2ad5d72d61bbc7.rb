class Prime

  class << self
    def nth(num)
      raise ArgumentError if num == 0

      prime_array = []
      prime_count = -1

      base_num = 2

      while prime_count < num - 1 do
        if is_prime?(base_num)
          prime_array.push(base_num)
          prime_count += 1
        end
        base_num += 1
      end

      prime_array[prime_count]
    end

    # return num if the num is prime value
    # We can make this method more faster using more inteligence algorithm.
    def is_prime?(num)
      return false if num == 1

      (2..num).each do |i|
        if (num % 2) == 0 && num != i
          return false
        end

        if (num % i) == 0 && num != i
          return false
        end
      end 

      num
    end
  end
end
