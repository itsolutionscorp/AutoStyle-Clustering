class Hamming
  def compute(one, two)
    one.chars.each_with_index.count{|item, i| item != two[i]}
  end
end
