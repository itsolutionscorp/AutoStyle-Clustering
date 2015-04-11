class Complement
  # gsub with hash as second parameter
  def self.of_dna(seq)
    repl_hash = { 'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A' }
    seq.gsub(/[CGAT]/, repl_hash)
  end
  
  def self.of_rna(seq)
    repl_hash = { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' }
    seq.gsub(/[GCUA]/, repl_hash)
  end
end
