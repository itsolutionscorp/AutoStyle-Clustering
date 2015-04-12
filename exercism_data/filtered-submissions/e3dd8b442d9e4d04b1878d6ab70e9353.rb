module Hamming
  def compute(one, two)
    one.to_s.chars.map.with_index{|x,i| x == two.to_s.chars[i]}.reject{|i| i}.count
  end
end
