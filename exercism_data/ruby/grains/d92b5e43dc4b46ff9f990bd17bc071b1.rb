class Grains
  @memo = {} # memoize our function for speed if we were using this in the real workd

  def square(num)
    if @memo[num].nil?
      # if we haven't memoized this, do it now.
      @memo[num] = 2**(num - 1)
    else
      # return the memo
      @memo[num]
    end
  end

  def total
    # shortcut to get the sum of numbers
    square(65) - 1
  end
end
