class PrimeFactors
  def self.for(num, results = [])
    return results if num == 1

    lowest_factor = if num.even?
                      2
                    else
                      (3..num/3).step(2).detect do |n|
                        num % n == 0
                      end || num
                    end

    PrimeFactors.for(num / lowest_factor, results.push(lowest_factor))

#     limit = num/2 > 5 ? num/2 : num
#     (2..limit).each_with_object([]) do |factor, arr|
#       while num % factor == 0
#         arr << factor 
#         num /= factor
#       end
#     end
  end
end
