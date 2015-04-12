class Hamming
  def compute(seq1, seq2)

    seq1Arr = seq1.chars
    seq2Arr = seq2.chars
    hamming  = 0
    seq1Arr.zip seq2Arr do |pair|
      break if pair[0].nil? or pair[1].nil?
      hamming += 1 if pair[0] != pair[1]
    end
    hamming
  end
end
