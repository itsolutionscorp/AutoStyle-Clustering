class Grains
  # class << self
  #   def square(num)
  #     @square||=Hash.new {|h,k| h[k]= k>1 ? h[k-1]*2 : 1}
  #     @square[num]
  #   end
  #
  #   def totals(num)
  #     @totals||=Hash.new {|h,k| h[k]= k>1 ? h[k-1]+square(k) : 1}
  #     @totals[num]
  #   end
  # end

  def square(num)
    # self.class.square(num)
    2 ** (num-1)
  end

  def total(board_size=64)
    # self.class.totals(board_size)
    2 ** board_size -1
  end
end
