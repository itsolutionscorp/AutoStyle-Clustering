def compute(strand, comparative_strand)
    mutations = 0
    strand.chars.each_with_index do |nucelotide, index|
      comparative_nucelotide = comparative_strand[index]
      mutations += 1 if comparative_nucelotide && nucelotide != comparative_nucelotide
    end
    mutations
  end
end