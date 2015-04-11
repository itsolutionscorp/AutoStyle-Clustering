class Complement

   DNA_NUCLEOTIDS = %w{G C T A}
   RNA_NUCLEOTIDS = %w{C G A U}


  def self.complement(str,list1,list2)
    output = ""
    str.length.times do |i|
      output << list2[list1.index str[i]]
    end
    output
  end

  def self.of_dna(dna_strand)
    complement dna_strand, DNA_NUCLEOTIDS, RNA_NUCLEOTIDS
  end

  def self.of_rna(rna_strand)
    complement rna_strand, RNA_NUCLEOTIDS, DNA_NUCLEOTIDS
  end

end
