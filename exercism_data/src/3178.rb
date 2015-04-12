class Hamming
  def compute(first, second)
    first.split("").each_with_index.collect{|item, index| item!=second.split("")[index] && !second.split("")[index].nil?}.count(true)
  end
end
