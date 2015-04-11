class Complement
  def self.of_dna(sequence)
    of(dna, sequence)
  end

  def self.of_rna(sequence)
    of(rna, sequence)
  end

  def self.dna
    { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end

  def self.rna
    dna.invert
  end

  def self.of(map, sequence)
    check_valid!(map, sequence)
    sequence.gsub(/./, map)
  end

  def self.check_valid!(map, sequence)
    raise ArgumentError unless (sequence.chars - map.keys).empty?
  end

  private_class_method :dna, :rna, :of, :check_valid!
end
