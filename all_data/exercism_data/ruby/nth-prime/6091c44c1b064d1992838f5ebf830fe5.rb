class Prime

  def self.nth(num)
    raise ArgumentError if num < 1
    highnumber = Float::INFINITY
    count = 0
    prime = 0
    (2..highnumber).each do |n|
      if isprime(n)
        count += 1
        prime = n
      end
      break if count == num
    end
  return prime
  end

  def self.isprime(num)
    sqrt = Math.sqrt(num).ceil
    (2..sqrt).select { |n| num % n == 0 && num != 2 }.empty?
  end
  
end
