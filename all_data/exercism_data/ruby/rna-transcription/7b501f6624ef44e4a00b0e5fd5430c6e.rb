module Complement
  DNA = ->(key){
    { 'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
  }[key]
  }
  RNA = ->(key) {
    { 'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
  }[key]
  }
  def of_dna(nucleotide)
    complement(DNA, nucleotide)
  end

  def of_rna(nucleotide)
    complement(RNA, nucleotide)
  end

  def complement strand, nucleotide
    nucleotide.chars.map {|nt|strand[nt]}.join
  end
end
Complement.extend(Complement)
