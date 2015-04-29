require 'pry-byebug'
class Trinary
  def initialize(t_num)
    @t_num = t_num
  end

  def to_decimal
    if @t_num.gsub(/\D/, "*").include? "*"
      0
    else
    indices = []
    @t_num.reverse.each_char.map {|c| c.to_i}.each_index { |x| indices << x }.zip(indices).map { |num, val| num * (3 ** val) }.inject { |acc, x| acc + x }
    end
  end
end
