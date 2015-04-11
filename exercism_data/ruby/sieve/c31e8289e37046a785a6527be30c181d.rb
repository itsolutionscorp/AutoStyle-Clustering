class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    vals = (3..@limit).step(2).to_a
    while (i ||= 0) < vals.length do
      filter(vals, vals[i]); i += 1
    end
    vals.unshift(2)
  end

  private
  def filter(vals, cur_prime)
    ((cur_prime * 2)..@limit).step(cur_prime).each{|m| vals.delete(m) }
  end
end
