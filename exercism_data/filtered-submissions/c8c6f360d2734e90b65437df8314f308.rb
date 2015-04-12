class Hamming
  def compute(seq1,seq2)
    cnt = 0
    seq1arr = seq1.split ''
    seq2arr = seq2.split ''
    seq1arr.each_index { |char|
      unless seq1arr[char].eql? seq2arr[char]
        cnt += 1
      end
    }
    cnt
  end
end
