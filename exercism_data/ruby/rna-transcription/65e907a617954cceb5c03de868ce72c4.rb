class Complement
  def self.of_dna strand
    dna_complements = { G: 'C', C: 'G', T: 'A', A: 'U'}
    strand.chars.map do |c|
      begin
        dna_complements.fetch(c.to_sym)
      rescue
        raise ArgumentError
      end
    end.join('')
  end

  def self.of_rna strand
    rna_complements = { C: 'G', G: 'C', A: 'T', U: 'A'}
    strand.chars.map do |c|
      begin
        rna_complements.fetch(c.to_sym)
      rescue
        raise ArgumentError
      end
    end.join('')
  end
end
