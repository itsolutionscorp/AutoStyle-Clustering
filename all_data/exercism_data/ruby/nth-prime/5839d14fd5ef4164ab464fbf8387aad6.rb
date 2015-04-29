# require 'prime'
# module NthPrime
#   def nth(num)
#     raise ArgumentError if num < 1
#     Prime::EratosthenesSieve.instance.get_nth_prime(num-1)
#     # get_nth_prime(num) || raise(ArgumentError)
#   end
# end
#
# Prime::extend ::NthPrime
#
class Prime
  @nums=[nil, nil,true,true, nil,true, nil,true, nil, nil, nil,true, nil,true, nil, nil, nil, true, nil, true, nil, nil, nil, true, nil, nil, nil, nil, nil, true, nil]
  class << self
    attr_accessor :nums
    attr_writer :primes

    def wheel_2_3_5
      [true, nil, nil, nil, nil, nil,true, nil, nil, nil,true, nil,true, nil, nil, nil,true, nil,true, nil, nil, nil,true, nil, nil, nil, nil, nil,true, nil]
    end

    def primes
      @primes||=nums.each_with_index.map{|x,y| y if x}.compact
    end

    def calculate_primes
      primes.each do |x|
        next if x < 7
        break if nums.size < x*x
        (x*[primes.last,x].min..nums.size).step(x) do |y|
          next unless nums[y]
          nums[y]=nil
        end
      end
      (primes.last+2..[nums.size,(primes.last-2)**2].min).step(2).each do |y|
        @primes.push(y) if nums[y]
      end
      @primes
    end

    def nth(num)
      raise ArgumentError if num < 1
      begin
        return primes[num-1] if num <= primes.size
        raise 'Not Found'
      rescue
        self.nums+=wheel_2_3_5*[primes.last-1,num/2].min
        calculate_primes
        retry
      end
    end
  end
end
