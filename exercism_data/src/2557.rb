class Hamming

  def compute(first, second)
     hamming_count = 0
     (0..[first.length, second.length].min - 1).each do |i|
       if first[i] != second[i]
          hamming_count = hamming_count + 1
       end
     end
     return hamming_count
  end



end
