module Complement

  def Complement.table(nucleotide, inverse)
    nucleotides = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    (inverse) ? nucleotides.key(nucleotide) : nucleotides[nucleotide]
  end

  def Complement.convert(strand, inverse)
    result = ''
    strand.each_char { |letter| result += table(letter, inverse) }
    result
  end

  def Complement.of_dna(strand)
    Complement.convert(strand, false)
  end

  def Complement.of_rna(strand)
    Complement.convert(strand, true)
  end

end
