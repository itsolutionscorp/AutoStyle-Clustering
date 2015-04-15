class Prime
  def self.nth(depth)
    raise ArgumentError if depth==0
    answer = 1
    depth.times { answer=next_prime_from(answer) }
    return answer
  end

  def self.next_prime_from(number)
    while true
      return number if is_prime?(number+=1)
    end
  end
  
  def self.is_prime?(number)
    (2...number).each {|div|
      if !divisible?(number,div)
        return false
      end
    }
    return true
  end

  def self.divisible?(number, divisor)
    (number.divmod(divisor))[1]==0 ? false : true
  end
end
