class Prime

  def self.nth(n)
    raise ArgumentError, "n should be greater than 0" if n == 0
    counter = 0
    i = 1
    until counter == n do
      i = i +1
      if i.check_prime
        p i
        counter = counter + 1
      end
    end
    return i
  end

end

class Fixnum

  def check_prime
    result = true
    upper_bound = self ** 0.5
    (2..upper_bound.round()).each do |n|
      result = false if self % (n) == 0
    end
    return result
  end

end
