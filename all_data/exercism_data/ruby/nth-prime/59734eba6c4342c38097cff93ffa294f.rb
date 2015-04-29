class Prime

  def self.nth(num)
    if num.class != Fixnum || num < 1
      raise ArgumentError
    end

    prime_arr = [2, 3, 5, 7]
    testing_num = 9

    while num > prime_arr.length

      
      testing_factor = 3
      prime = true

      while prime == true && testing_factor <= (testing_num/2).floor
        if testing_num % testing_factor == 0
          prime = false
        end
        testing_factor += 1
      end

      if prime
        prime_arr << testing_num
      end

      testing_num += 2

    end

    return prime_arr[num - 1]

  end

end
