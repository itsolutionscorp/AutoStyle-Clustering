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

  TAIL = 1..-1

  def Complement.of_dna(dna, acc = '')
    return acc if dna.length.zero?
    Complement.of_dna(dna[TAIL], acc+COMPLEMENT_DNA[dna.chars.first])
  end

  def Complement.of_rna(rna, acc = '')
    return acc if rna.length.zero?
    Complement.of_rna(rna[TAIL], acc+COMPLEMENT_RNA[rna.chars.first])
  end
end
