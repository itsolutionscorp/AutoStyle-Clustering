class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    @primes ||= prime_list
  end

  def prime_list
    a = (2..@limit).to_a
    a.each_with_object([]) do | current_number, list |
      list << current_number
      a.reject! { | n | n%current_number == 0 && n > current_number }
    end
  end
end
