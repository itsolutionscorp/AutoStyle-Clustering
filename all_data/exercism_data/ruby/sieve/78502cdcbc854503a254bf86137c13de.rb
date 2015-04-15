class Sieve

  def initialize n
    @max = n
  end
  
  def primes
    nums = [*2..@max]

    range.each do |i|
      (i..@max/i).each{ |j| nums.delete i*j }
    end

    nums
  end
  
  private
  def range
    (2..Math.sqrt(@max))
  end

end
