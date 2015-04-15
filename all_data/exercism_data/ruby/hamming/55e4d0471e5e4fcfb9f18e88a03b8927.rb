class Hamming

  def self.compute(strand1, strand2)
    final_count = 0
    strand1.each_with_index do |strand, i|
      n = 0
      while n <= strand2.length
        if strand != strand2[i]
          final_count += 1
        end
        n += 1
      end
    end
    final_count
  end

end
