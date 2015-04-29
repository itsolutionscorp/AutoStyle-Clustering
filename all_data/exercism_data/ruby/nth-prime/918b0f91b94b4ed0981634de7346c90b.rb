class Prime
  def self.nth(n)
    raise ArgumentError.new if n < 1
    count = 0
    i = 1
    while count < n
      i += 1
      count += 1 if is_prime(i)
    end

    i
  end

  def self.is_prime(i)
    (2...i).each do |factor|
      return false if (i % factor == 0)
    end
    true
  end
end
