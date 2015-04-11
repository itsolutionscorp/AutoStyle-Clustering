class Prime
  def self.nth(i)
    raise ArgumentError if i < 1

    output = 1
    counter = 0

    until counter==i do
      output +=1

      if isPrime?(output)
        counter +=1
      end
    end

    output
  end

  def self.isPrime?(i)
    if i<1
      raise ArgumentException, "must be positive number"
    end

    result = true

    if i == 2
      result = true
    elsif i % 2 ==0
      result = false
    else
      factor = 2

      # factor int divisor = 3;
      upperLimit = Math.sqrt(i +1)

      while (factor <= upperLimit)
          if i % factor == 0
            result = false
          end
        factor +=1
      end
    end
    result
  end
end

