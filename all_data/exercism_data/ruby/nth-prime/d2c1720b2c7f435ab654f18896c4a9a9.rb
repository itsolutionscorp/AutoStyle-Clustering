module Prime
  class << self
    def nth(n)
      raise ArgumentError if n <= 0
      prime = Fixnum
      i = 2
      until n == 0
        if is_prime?(i)
          prime = i
          n -= 1
        end
        i +=1
      end
      prime
    end

    def is_prime?(n)
      (2..n-1).each do |i|
        return false if n % i == 0 
      end
      true
    end
  end
end
