class Hamming
  def compute(seq1,seq2)
    res = []
    seq1.length.times do |n|
     res << (seq1[n] == seq2[n] ? 0:1)
    end
    sum = res.reduce(:+)
  end
end
