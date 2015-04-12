class Hamming
  
  def compute(input1, input2)
    strand1 = input1.split(/w*/)
    strand2 = input2.split(/w*/)
    
    length = [strand1.length, strand2.length].max
    
    length.times.map do |n|
      strand1[n] == strand2[n] ? 0 : 1
    end.inject(&:+)
  end
  
  
  
end
