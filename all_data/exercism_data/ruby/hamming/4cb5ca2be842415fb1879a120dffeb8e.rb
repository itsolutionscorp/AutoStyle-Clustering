class Hamming

  def self.compute(seq1, seq2)
    count = 0
    seq1.chars.each_with_index do |letter, idx|
      break if seq2[idx].nil?
      count += 1 unless letter.eql? seq2[idx]
    end
    return count
  end

end
