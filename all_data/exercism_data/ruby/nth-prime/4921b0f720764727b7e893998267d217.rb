class Prime

  def self.nth(prime_number_index)
    raise ArgumentError if prime_number_index == 0
    integer=2
    prime_arrays = []
    while prime_arrays.length != prime_number_index
      prime_arrays << integer if is_prime(integer)
      if integer == 2
        integer+=1
      else
        integer+=2
      end
    end
    prime_arrays[prime_number_index-1]
  end

  private

  def self.is_prime(integer)
    prime_array = []
    (1..integer).map { |x| prime_array << x if integer % x == 0 }
    return prime_array[1] if prime_array.length == 2
  end
end
