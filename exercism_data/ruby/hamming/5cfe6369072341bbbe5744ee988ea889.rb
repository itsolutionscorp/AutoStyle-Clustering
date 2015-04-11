class Hamming
  def self.compute(a, b)
    differences = 0
    a,b = b,a if b.length > a.length
    a.split("").each_with_index do |char, i|
      break unless b[i]
      if char != b[i] 
        differences += 1
      end
    end
    differences
  end
end
