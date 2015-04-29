class Prime

  def self.nth(index)
    fail ArgumentError if index < 1 || !index.is_a?(Integer)
    (2..Float::INFINITY).lazy.select { |n| prime?(n) }[index-1]
  end

  def self.prime?(prime)
    return true  if prime == 2
    return false if prime == 1
    return false if [2, 3, 5, 7, 9, 11, 13, 17].any? { |n| prime != n && prime % n == 0 }

    (19..(prime / 2)).all? { |n| prime % n != 0 }
  end
end
