class Hamming

  def self.compute(a, b)
    i = 0
    counter = 0
    a.each_char do |n|
      break if b[i] == nil
      counter += 1 if n != b[i]
      i += 1
    end
    counter
  end  

end
