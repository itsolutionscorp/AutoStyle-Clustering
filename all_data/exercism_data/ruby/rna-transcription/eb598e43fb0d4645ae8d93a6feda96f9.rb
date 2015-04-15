class Complement
  COMPLEMENT_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  COMPLEMENT_RNA = {
    'G' => 'C',
    'C' => 'G',
    #'T' => 'A',
    'U' => 'A',
    'A' => 'T',
  }

  def Complement.of_dna(dna)
    recurse(dna, COMPLEMENT_DNA)
  end

  def Complement.of_rna(rna)
    recurse(rna, COMPLEMENT_RNA)
  end

  private
    TAIL = 1..-1
    def Complement.recurse(strand, complement, acc='')
      return acc if strand.length.zero?
      recurse(strand[TAIL], complement, acc+complement[strand.chars.first])
    end
end
