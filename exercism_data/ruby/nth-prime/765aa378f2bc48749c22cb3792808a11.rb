class Prime
  def self.is_prime?(num)
    (2..(Math.sqrt(num))).each {|i| return false if num % i == 0}
    return true
  end
   
  def self.nth(n)

    raise ArgumentError.new('You must enter a number') if n == nil or n == 0
   
    return 2 if n == 1

    num = 3
    index = 1
   
    while true
      index += 1 if self.is_prime?(num)
      return num if index == n
      num += 2
    end
  end
end
