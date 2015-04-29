class Hamming
  def self.compute(seq1, seq2)
    length = [seq1.to_s.length, seq2.to_s.length].min
    sum = 0

    (0..length - 1).each do |i|
      sum += 1 unless seq1.to_s[i] == seq2.to_s[i]
    end

    sum
  end
end
