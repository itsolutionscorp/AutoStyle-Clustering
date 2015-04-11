class Prime

  def self.nth(num)
    if num == 0
      raise ArgumentError, "zero is not a prime"
    else
      prime_numbers = [2]
      until prime_numbers.count > num
        i = prime_numbers.last
        contains_prime = [true]
        until contains_prime.include?(true) == false
          i += 1
          contains_prime = prime_numbers.map do |prime|
            i % prime == 0
          end
        end
        prime_numbers << i
      end
      prime_numbers[num - 1]

    end
  end

end
