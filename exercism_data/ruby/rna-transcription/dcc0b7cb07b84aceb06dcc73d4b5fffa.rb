class Complement
  @@dna_to_rna_mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  @@rna_to_dna_mapping = {'G': 'C', 'C': 'G', 'A': 'T', 'U': 'A'}
  puts @@rna_to_dna_mapping
  def self.of_dna(sequence)
    output = ''
    sequence.each_char do |ch|
      if @@dna_to_rna_mapping[ch.to_sym]
        output += @@dna_to_rna_mapping[ch.to_sym]
      else
        raise ArgumentError, 'Invalid Sequence'
      end
    end
    output
  end

  def self.of_rna(sequence)
    output = ''
    sequence.each_char do |ch|
      if @@rna_to_dna_mapping[ch.to_sym]
        output += @@rna_to_dna_mapping[ch.to_sym]
      else
        raise ArgumentError, 'Invalid Sequence'
      end
    end
    output
  end
end
