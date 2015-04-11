class Sieve

  def initialize n
    @max = n
  end
  
  def primes
    nums = [nil, nil, *2..@max]

    (2..Math.sqrt(@max)).each do |i|
      (i..@max/i).each{ |j| nums[i*j] = nil } if nums[i]
    end

    nums.compact
  end

end
