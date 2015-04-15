class Prime
  def self.nth(index)
    check_valid_number(index)
    @primes_array ||= []
    @primes_array[index-1] || get_prime(index-1)
  end

  private

  def self.check_valid_number(index)
    raise ArgumentError unless index.kind_of?(Integer) && index > 0
  end

  def self.get_prime(index)
    while(@primes_array.length < index + 1)
      get_next_prime
    end
    @primes_array[index]
  end

  def self.get_next_prime
    original_length = @primes_array.length
    number_to_check = (@primes_array.max || 1) + 1

    while @primes_array.length == original_length do
      @primes_array << number_to_check if is_prime?(number_to_check)
      number_to_check += 1
    end
  end

  def self.is_prime?(number)
    i = 2
    while i <= number/2 do
      return false if number % i == 0
      i += 1
    end
    true
  end
end
