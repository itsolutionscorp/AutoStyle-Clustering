class Hamming
  def self.compute strand1_text, strand2_text
    strand1 = strand1_text.split("")
    strand2 = strand2_text.split("")
    strand1 = strand1[0..strand2.count - 1]
    self.count_differences(strand1, strand2)
  end

  def self.count_differences(strand1, strand2)
    total = 0
    strand1.each_with_index do |nucleotide,i|
      if strand1[i] != strand2[i] then
        total += 1
      end
    end
    total
  end
end
