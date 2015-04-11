class Prime
  def self.nth(prime_index)
    raise ArgumentError if prime_index < 1
    prime = 1
    prime_count = 0
    while prime_count < prime_index
      prime += 1
      count = 0
      (2..prime).each do |n|
        count += 1 if prime % n == 0
        break if count > 1
      end
      prime_count +=1 if count == 1
    end
    prime
  end
end
