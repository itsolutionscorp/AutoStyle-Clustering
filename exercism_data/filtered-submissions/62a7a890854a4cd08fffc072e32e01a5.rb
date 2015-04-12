class Hamming
  def compute(arg, arg2) 
    strand1 = arg.split(//)
    strand2 = arg2.split(//)
    distance = 0
    strand1.length.times do |i| 
      if strand1[i] != strand2[i]
        distance += 1
      end
    end
    distance
  end
end
