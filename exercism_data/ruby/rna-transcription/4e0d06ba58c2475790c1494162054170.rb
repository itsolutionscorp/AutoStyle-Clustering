class Complement
  def self.of_dna(string)
    @dna_to_rna ||= dna_nucleotides

    rna = ''
    string.each_char { |c| rna << @dna_to_rna[c] }
    rna
  end

  def self.of_rna(string)
    @rna_to_dna ||= rna_nucleotides

    dna = '' 
    string.each_char { |c| dna << @rna_to_dna[c] }
    dna
  end

  def self.dna_nucleotides
    dna  = Hash.new do |_, k|
      msg = "ArgumentError: invalid nucleotide #{k} for ('G', 'C', 'T', or 'A')"
      msg << 'did you mean #of_rna ?' if k == 'U'
      fail ArgumentError, msg
    end

    dna.merge!('G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.rna_nucleotides
    rna  = Hash.new do |_, k|
      msg = "ArgumentError: invalid nucleotide #{k} for ('G', 'C', 'U', or 'A')"
      msg << 'did you mean #of_rna ?' if k == 'T'
      fail ArgumentError, msg
    end

    rna.merge!('G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T')
  end
end
