class Complement

  DNA_RNA = {'A' => 'U', 'T' => 'A', 'C' => 'G', 'G' => 'C'}

  def self.of_dna(dna)
    rna = ""
    dna.chars.each do |n|
      rna << DNA_RNA.values_at(n).join
    end

    rna
  end

  def self.of_rna(rna)
    dna = ""
    rna.chars.each do |n|
      dna << DNA_RNA.key(n)
    end

    dna
  end

end
