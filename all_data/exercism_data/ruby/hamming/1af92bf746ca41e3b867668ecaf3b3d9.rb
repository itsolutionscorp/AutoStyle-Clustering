class Hamming
  def self.compute(d1, d2)
    hamming_distance = 0
    strand1 = d1.split('')
    strand2 = d2.split('')
    strand1.each_with_index do |nucleo, index| 
      hamming_distance += 1 unless(nucleo == strand2[index] || strand2[index].nil? ) 
    end
    hamming_distance
  end
end
