class Hamming

  def self.compute(strand1, strand2)
    compare_strands(strand1, strand2)
  end

  def self.compare_strands(strand1, strand2)
    hamming_count = 0
    zipped = strand1.chars.zip(strand2.chars)
    zipped.each do |pair|
      unless pair[1] == nil
        if pair[0] != pair[1]
          hamming_count += 1
        end
      end
    end 
    hamming_count
  end
end
