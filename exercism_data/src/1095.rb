module Hamming
  def compute(sequence1, sequence2)
    index, difference = 0
    difference = 0
    until sequence1[index].nil? || sequence2[index].nil?
      difference +=1 unless sequence1[index] == sequence2[index]
      index += 1
    end
    difference
  end
end
