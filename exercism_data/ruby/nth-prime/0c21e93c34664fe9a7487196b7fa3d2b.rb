class Prime
  def self.nth(count)
    raise ArgumentError if count == 0
    num = 2
    cnt = 1
    while true
      cnt += 1 if self.prime?(num)
      return num if cnt == count
      num += 1
    end
  end

  def self.prime?(num)
    return false if num % 2 == 0
    i = 3
    while i <= num / i
      return false if num % i == 0
      i += 2
    end
    true
  end
end
