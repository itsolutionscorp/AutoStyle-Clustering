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
    number_array = @t_num.reverse.each_char.map {|c| c.to_i}
    number_array.each_index { |x| indices << x }
    number_index_pairs = number_array.zip(indices)
    products = number_index_pairs.map { |num, index| num * (3 ** index) }
    sum_of_products = products.inject { |sum, product| sum + product}
    end
  end
end
