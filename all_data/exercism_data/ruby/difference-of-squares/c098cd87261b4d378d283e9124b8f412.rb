module SquareCache
  
  class SequentialCache
    def initialize(function)
      @cache = [0]
      @function = function
    end

    def result(n)
      if n < 1
        return 0
      end

      if not @cache[n]
        (@cache.length .. n).each do |index|
          previous_result = @cache[index-1]
          @cache[index] = @function.call(index,previous_result)
        end
      end
      return @cache[n]
    end
  end
  
  @@sum_of_sqaures_cache = SequentialCache.new(
    proc do |this_number, previous_result|
      previous_result + this_number**2
    end
  )

  @@square_of_sums_cache = SequentialCache.new( 
    proc do |this_number, previous_result|
      previous_result + this_number**2 * (this_number-1) + this_number**2
    end
  )

  @@difference_cache = SequentialCache.new(
    # previous_result argument isn't needed in this proc, included for consistency
    proc do |this_number,previous_result|
      @@square_of_sums_cache.result(this_number) - @@sum_of_sqaures_cache.result(this_number)
    end
  )

end

# The test case insists on calling Squares.new()
# if it hadn't, I would have done the whole thing as a module
class Squares
  include SquareCache

  def initialize(n)
    @n = n
  end
  def sum_of_squares
    @@sum_of_sqaures_cache.result(@n)
  end
  def square_of_sums
    @@square_of_sums_cache.result(@n)
  end
  def difference
    @@difference_cache.result(@n)
  end
end
