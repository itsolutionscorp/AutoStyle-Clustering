class Sieve

  attr_reader :candidates

  def initialize(limit)
    @candidates = Hash.new()
    (2..limit).each { |x| candidates[x] = true}
  end

  def primes()
    candidates.keys.each do |current|
      candidates.each do |k, v|
        if k % current == 0 and k != current
          candidates[k] = false
        end
      end
    end

    return candidates.reject { |k,v| v == false }.keys

  end
end
