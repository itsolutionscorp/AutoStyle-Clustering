class Trinary
  attr_reader :num
  def initialize(num)
    @num = num
  end

  def to_decimal
    total = 0
    numarr = num.to_s.chars.map(&:to_i)
    numarr.each_with_index do |num, index|
      total += num * 3**(numarr.size-index-1)
    end
    total
  end
end
