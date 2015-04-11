class Prime
  # Using Eratosthenes sieve
  def self.nth(findPrime)
    count = 1
    max_prime = 2 # All even numbers after to are not prime, so we can skip them quick

    if ! findPrime.kind_of? Fixnum then
      raise ArgumentError, "Argument must be a Fixed Number."
    elsif findPrime == 0 then
      raise ArgumentError, "Illegal Argument: 0"
    elsif findPrime == 1 then
      return max_prime
    end
    max_size = findPrime * 20
    b = Array.new(max_size,true)

    3.step(max_size, 2) do |i|
      next unless b[i]
      count += 1
      max_prime = i
      break if count >= findPrime
      i.step(max_size, 2*i) { |k| b[k] = nil }
    end
    max_prime
  end
end
