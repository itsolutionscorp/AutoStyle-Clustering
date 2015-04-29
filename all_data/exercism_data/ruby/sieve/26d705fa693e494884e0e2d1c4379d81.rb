class Sieve
  def initialize(limit)
    @range = (2..limit).to_a
  end

  def primes
    @range.map.with_index do |candidate, index|
      next unless candidate
      mark_composites(index, candidate)
      candidate
    end.compact
  end

  private

  def mark_composites(from, step)
    first = from + step
    (first...@range.count).step(step).each do |i|
      @range[i] = nil
    end
  end
end
