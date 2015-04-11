class Hamming

  def self.compute(first, second)
    ham_length = 0
    max_strand_length = [first, second].sort_by(&:length)[0].length
    i = 0
    while i < max_strand_length
      ham_length += 1 if first[i] != second[i]
      i += 1
    end
    ham_length
  end

end
