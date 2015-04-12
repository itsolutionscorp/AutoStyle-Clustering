class Hamming
  def compute(first, second)
    first.split(//)
    second.split(//)
    count = 0
    for i in 0..first.length - 1
      if first[i] != second[i]
        count = count + 1
      end
    end 
    return count
  end
end
