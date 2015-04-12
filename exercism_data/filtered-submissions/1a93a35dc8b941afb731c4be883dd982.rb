class Hamming

  def compute(strand1, strand2)
    diff = 0
    strand1.chars.each_with_index do |dna, index|
      if index < strand2.length
        diff+=1 if dna != strand2[index]
      end
    end
    diff
  end
end
