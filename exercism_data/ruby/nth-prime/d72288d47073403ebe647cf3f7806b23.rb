class Prime

  def self.nth(int)
    raise ArgumentError if int < 1
    numbers = []

    a = 2

    loop do

      break if numbers.size == int
      numbers << a if is_prime?(a)

      a += 1
    end

    numbers[int - 1]
  end

  def self.is_prime?(i)
    a = 2
    loop do
      if i == a
        return true
      elsif i % a == 0
        return false
      else
        a += 1
      end
    end
  end

end
