module Prime
  def self.nth n
    raise ArgumentError if n <= 0
    if n == 1
      2
    else
      acc = 1
      i = 3
      loop do
        acc += 1 if i.prime?
        break if acc == n
        i += 2
      end
      i
    end
  end
end

class Integer
  def prime?
    res = true
    3.upto(Math.sqrt(self)) do |i|
      if self % i == 0
        res = false
        break
      end
    end
    res
  end
end
