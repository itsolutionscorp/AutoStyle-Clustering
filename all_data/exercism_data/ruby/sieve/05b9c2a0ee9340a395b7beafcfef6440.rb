class Sieve
  def initialize(limit)
    @limit = limit
  end
  def primes
    output = Hash[(2..@limit).map { |num| [num, true] }]
    (2..Math.sqrt(@limit)).each do |num| 
      if output[num] then
        (1..(@limit/num)).each { |multiply| output[num**2 + (multiply - 1)*num] = false }
      end
    end
    output.select{ |k, v| k if v }.keys
  end
end
