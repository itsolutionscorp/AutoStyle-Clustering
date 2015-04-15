class Year
  def self.leap?(y)
    return true if divisible?(y,400)
    return false if divisible?(y,100)
    return divisible?(y,4)
  end
end

def divisible?(x, n)
  return x % n == 0
end
