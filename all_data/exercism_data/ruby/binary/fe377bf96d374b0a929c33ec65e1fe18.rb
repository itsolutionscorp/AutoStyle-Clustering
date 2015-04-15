class Binary
  attr_accessor :num

  def initialize(num)
    if /^[0-1]+$/.match(num).nil?
      @num = 0
    else
      @num = num
    end
  end

  def to_decimal
    return 0 unless @num.to_i > 0

    arr = (0..@num.length - 1).to_a
    arr.map! { |x| 2**(x) * @num.reverse[x].to_i }
    arr.inject(:+)
  end
end
