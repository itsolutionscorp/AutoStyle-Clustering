class Complement
  def self.of(na, from, to)
    if na.split(//).all? {|c| from.include? c}
      na.tr(from, to)
    else
      raise ArgumentError
    end
  end

  def self.of_dna(dna)
    of(dna, 'CGTA', 'GCAU')
  end

  def self.of_rna(rna)
    of(rna, 'GCAU', 'CGTA')
  end

  private_class_method :of
end
