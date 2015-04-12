class Hamming

  def compute(first, second)
    first.size.times.count { |i| arr1[i] == arr2[i] }
  end

end
