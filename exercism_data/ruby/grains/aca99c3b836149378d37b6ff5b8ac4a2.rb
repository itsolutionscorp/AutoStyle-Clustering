class Grains
  class << self
    def square(num)
      @square||=Hash.new {|h,k| h[k]= k>1 ? 2**(k-1) : 1}
      @square[num]
    end

    def totals(num)
      @totals||=Hash.new {|h,k| h[k]= k>1 ? 2**k-1 : 1}
      @totals[num]
    end
  end

  def square(num)
    self.class.square(num)
  end

  def total(board_size=64)
    self.class.totals(board_size)
  end
end
