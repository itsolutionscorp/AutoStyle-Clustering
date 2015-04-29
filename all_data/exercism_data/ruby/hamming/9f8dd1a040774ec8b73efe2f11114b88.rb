class Hamming
  def self.compute(input1, input2)
    minlength = [input1.length, input2.length].min
    result = 0
    
    minlength.times do |i|
      if input1[i] != input2[i]
        result = result + 1
      end
      i += 1
    end
    result
  end
end
