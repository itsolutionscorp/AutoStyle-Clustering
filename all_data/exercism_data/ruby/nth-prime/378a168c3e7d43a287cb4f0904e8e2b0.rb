class Fixnum
  def prime?
    return true if (2..3).include?(self)
    return false if self == 1 || self.even?

    i = 3
    while i < (self * 0.5 + 1)
      return false if self % i == 0
      i += 2
    end
    true
  end
end

class Prime
  def self.nth(target)
    raise ArgumentError if target == 0

    i, k = 0, 0
    until k == target
      k += 1 if i.prime?
      i += 1
    end
    i - 1
  end
end
