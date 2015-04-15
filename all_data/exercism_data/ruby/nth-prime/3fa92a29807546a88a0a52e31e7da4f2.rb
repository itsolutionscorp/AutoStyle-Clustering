# Nth prime class

class Prime
  # List of primes, each entry is odd and the first is for 3
  @prime_list = [true, true, true]   # 3, 5, and 7

  class << self
    attr_accessor :prime_list
  end

  # 0th (invalid) and 1st are taken care of here
  def self.nth( ord )
    raise ArgumentError if ord < 1
    return 2 if ord == 1

    new( ord ).nth
  end

  def initialize( ord )
    @ord = ord
  end

  # Attempt to find the nth prime, extending the list as necessary
  def nth
    loop do
      value = search( @ord )
      return value unless value.nil?
      build_out_prime_list
    end
  end

  private

  # Access the class list of primes
  def prime_list
    self.class.prime_list
  end

  # Search through the list for the ord'th prime. It may not be there, in which case
  # the list will be extended
  def search( ord )
    prime_list.each_with_index do |prime, idx|
      next unless prime

      return idx_to_value( idx ) if (ord -= 1) == 1
    end

    nil
  end

  # Extend the prime list by doubling its size each time.
  def build_out_prime_list
    new_size = prime_list.size + prime_list.size
    loop_end = Math.sqrt( idx_to_value( new_size ) ).ceil

    (prime_list.size..new_size).each { |idx| prime_list[idx] = true }

    prime_list.each_with_index do |prime, idx|
      next unless prime

      divisor = idx_to_value( idx )
      break if divisor > loop_end

      (idx+1..new_size).each { |i| prime_list[i] = false if idx_to_value( i ) % divisor == 0 }
    end
  end

  def idx_to_value( idx )
    idx * 2 + 3
  end
end
