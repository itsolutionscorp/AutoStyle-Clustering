# Use the power of MATH to do this work for us
class Grains
  def square(num)
    2**(num-1)
  end

  def total
    square(65) - 1
  end
end

# Use recursion to find the solution. Included for completeness sake.
class GrainsRecursive
  def initialize
    @square = []
  end

  def square(num)
    return 1 if num == 1 # Base case
    @square[num] ||= 2*square(num-1)
  end

  def total
    @total ||= build_total(64)
  end

  private

  # Recursively build the total number of wheat on the entire board
  # 1. Check to see whether there's work left to do
  # 2. Add the current square to all the squares before
  def build_total(num)
    if num == 1
      1 # No meaningful work left to do, just return the base case
    else
      square(num) + build_total(num-1)
    end
  end

  # Slightly faster, but harder to read tail-recursive strategy
  # def build_total(num, sum=0)
  #   if num == 0
  #     sum
  #   else
  #     build_total(num-1, sum+square(num))
  #   end
  # end

end
