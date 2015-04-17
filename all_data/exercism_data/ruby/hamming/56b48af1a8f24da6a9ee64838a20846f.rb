class Hamming

  def self.compute(strand1, strand2)
    hamming_difference = 0
    strand1.split('').zip(strand2.split('')).each do |a1,a2|
      break if a1.nil? or a2.nil?
      hamming_difference += 1 if a1 != a2
    end
    hamming_difference
  end
end