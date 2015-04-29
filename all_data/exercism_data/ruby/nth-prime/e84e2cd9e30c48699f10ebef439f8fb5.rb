class Prime
  def self.nth(n)
    raise ArgumentError if n.zero?
    subject = 1
    found = 0

    while found < n do
      subject += 1
      if is_prime?(subject)
        found += 1
      end
    end
    subject
  end

  def self.is_prime?(number)
    flag = true
    (2...number).each do |factor|
      if number % factor == 0
        flag = false
        break
      end
    end
    flag
  end
end
