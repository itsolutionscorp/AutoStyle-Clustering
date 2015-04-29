class Hamming
  def self.compute(a,b)
    arr1 = a.split('')
    arr2 = b.split('')
    count = 0
    
    arr1.length.times do |i|
      if arr1[i] != arr2[i]
        count = count + 1
      end
    end
    
    return count
  end
end
