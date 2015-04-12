class Hamming
  
  def compute(arg1, arg2)
    miss_match = 0
    arg1.size.times { |i| miss_match += 1 if arg1[i] != arg2[i] }
    miss_match
  end
  
end
