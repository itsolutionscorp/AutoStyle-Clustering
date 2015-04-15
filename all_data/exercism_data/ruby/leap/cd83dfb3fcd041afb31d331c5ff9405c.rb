class Fixnum
  def is_evenly_divisible_by(n)
    self % n == 0
  end
end

class Year
  def self.leap?(n)
    (n.is_evenly_divisible_by(4) && !n.is_evenly_divisible_by(100)) ||
      n.is_evenly_divisible_by(400)
  end
end
