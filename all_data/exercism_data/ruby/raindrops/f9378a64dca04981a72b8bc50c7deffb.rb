require 'prime'

class Raindrops

  # This is horrible
  def self.convert(num)
    numbers = get_primes(num).inject([]) do |arr, n|
      arr << 'Pling' if n.eql? 3
      arr << 'Plang' if n.eql? 5
      arr << 'Plong' if n.eql? 7
      arr
    end
    numbers.empty? ? num.to_s : numbers.join
  end

  def self.get_primes(num)
    num.prime_division.flatten.uniq.sort
  end

end
