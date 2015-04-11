require 'pry'
class Prime
  def self.nth(depth)
    raise ArgumentError if depth==0
    prime=1
    (depth-1).times { prime=next_prime_from(prime) }
    next_prime_from(prime)
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
