class Hamming

  def self.compute(strand1, strand2)
    count, bucket  = [0, []]
    
    bucket = make_array(strand1)

    bucket.each_with_index do |char, i| 
      count+= 1 if char != strand2[i] && i < strand2.length 
    end
    count
  end

  def self.make_array(strand)
    array =[]
    strand.each_char{ |char| array << char }
    array 
  end

end
