class Hamming
  def self.compute(seq1, seq2)

    seq1Arr = seq1.split("")
    seq2Arr = seq2.split("")
    position = 0
    hamming = 0
    while(position < seq1Arr.size or position < seq2Arr.size)
      if seq1Arr[position].nil? or seq2Arr[position].nil?
        break
      end
      if seq1Arr[position] != seq2Arr[position]
        hamming += 1
      end
      position +=1
    end
    hamming
  end
end
