class Complement
  DIC = {'G' => 'C',
         'C' => 'G',
         'T' => 'A',
         'A' => 'U'}

  def self.of_dna(dna)
    dna = dna.scan /\w/
    rna = ''

    dna.each { |x| rna += DIC[x] }
    rna
  end

  def self.of_rna(rna)
    rna = rna.scan /\w/
    dna = ''

    rna.each { |x| dna += DIC.invert[x] }
    dna
  end
end

