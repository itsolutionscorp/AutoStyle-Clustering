module Complement
  def self.of_dna s
    s.gsub('A', 'U').gsub('T', 'A').gsub('C', 'F').gsub('G', 'C').gsub('F', 'G')
  end
  def self.of_rna s
    s.gsub('A','T').gsub('U', 'A').gsub('C', 'F').gsub('G', 'C').gsub('F', 'G')

  end
end
