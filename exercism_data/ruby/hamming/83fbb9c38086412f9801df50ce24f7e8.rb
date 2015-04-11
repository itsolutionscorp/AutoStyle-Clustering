class Hamming
  def self.compute(a, b)
    hamming_number = 0
    min_length = [a.length,b.length].min
    (0..min_length-1).each do |i|
      hamming_number += 1 unless a[i] == b[i]
    end
    hamming_number
  end
end
