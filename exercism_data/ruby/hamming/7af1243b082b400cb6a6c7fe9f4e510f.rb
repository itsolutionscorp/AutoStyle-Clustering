class Hamming
  def self.compute(first, second)
    return -1 if first.length != second.length
    count = 0
    (0..first.length-1).each do |i|
      if first[i].downcase != second[i].downcase
        count+=1
      end
    end
    return count   
  end
end
