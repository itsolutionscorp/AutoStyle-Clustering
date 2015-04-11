class Sieve
  attr_reader :range

  def initialize n
    @range = *2..n
  end

  def primes
    r = range
    range[0...-1].each_with_index do |e, i|
      r[i + 1..-1].each do |ee|
        r[r.index(ee)] = nil if (ee % e == 0) unless ee.nil?
      end
    end
    r.compact
  end
end
