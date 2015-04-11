class Sieve
  def initialize n
    @n = n
  end

  def primes
    entries = (2..@n).entries
    entries.each { |entry| entries = filter_composites(entries, entry) }
    entries
  end

  def filter_composites entries, prime_num
    entries.reject { |entry| entry != prime_num and entry % prime_num == 0 }
  end
end
