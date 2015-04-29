class Complement
  @key = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(strand)
    strand.split('').map { |nuc| @key[nuc] }.join('')
  end

  def self.of_rna(strand)
    strand.split('').map { |nuc| @key.invert[nuc] }.join('')
  end
end
