class Sieve
  attr_accessor :upper_bound
  private
  @nums=[nil, nil, true, true, nil, true, nil, true, nil, nil, nil, true, nil, true, nil, nil, nil, true, nil, true, nil, nil, nil, true, nil, nil, nil, nil, nil, true, nil]
  class << self
    attr_accessor :nums
    attr_writer :primes

    def wheel_2_3_5
      [true, nil, nil, nil, nil, nil, true, nil, nil, nil, true, nil, true, nil, nil, nil, true, nil, true, nil, nil, nil, true, nil, nil, nil, nil, nil, true, nil]
    end

    def primes
      @primes||=nums.each_with_index.map { |x, y| y if x }.compact
    end

    def calculate_primes
      primes.each do |x|
        next if x < 7
        break if nums.size < x*x
        (x*[primes.last, x].min..nums.size).step(x) do |y|
          next unless nums[y]
          nums[y]=nil
        end
      end
      (primes.last+2..[nums.size, (primes.last-2)**2].min).step(2).each do |y|
        @primes.push(y) if nums[y]
      end
      @primes
    end

    def primes_upto(num)
      while num > nums.size
        diff     =(num-nums.size/30)+1
        self.nums+=wheel_2_3_5*[primes.last-1, diff].min
        calculate_primes
      end
      primes.select { |p| p<num }
    end
  end

  def initialize(number)
    self.upper_bound=number
  end

  public
  def primes
    self.class.primes_upto(upper_bound)
  end
end
