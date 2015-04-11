class Complement
  PAIRS = {'U' => 'A', 'T' => 'A', 'C' => 'G', 'G' => 'C'}

  def self.of_dna(strand)
    transcribe(strand, 'U')
  end

  def self.of_rna(strand)
    transcribe(strand, 'T')
  end

  def self.transcribe(strand, a_complement)
    bases = strand.upcase.chars
    valid = "UTACG".delete(a_complement)
    fail ArgumentError, "Base not in #{valid}" unless bases.all? do |base|
      valid.include?(base)
    end

    bases.map { |base| PAIRS.fetch(base, a_complement) }.join
  end

  private_class_method :transcribe

end
