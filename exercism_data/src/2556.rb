class Hamming
  def compute(seq1, seq2)

    seq1Arr = seq1.split("")
    seq2Arr = seq2.split("")
    seq1Arrsize = seq1Arr.size
    seq2Arrsize = seq2Arr.size
    position, hamming  = 0, 0
    
    while(position < seq1Arrsize or position < seq2Arrsize)
      break if seq1Arr[position].nil? or seq2Arr[position].nil?
      hamming += 1 if seq1Arr[position] != seq2Arr[position]
      position +=1
    end
    hamming
  end
end
