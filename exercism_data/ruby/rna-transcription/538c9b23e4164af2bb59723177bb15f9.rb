class Complement
  # version 3
  def self.of_dna(dna)
    dna.tr('GCTA', 'CGAU')
  end

  def self.of_rna(rna)
    rna.tr('CGAU', 'GCTA')
  end

  # version 1
  # DNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }.freeze
  # RNA = DNA.invert

  # class << self
  #   [:dna, :rna].each do |name|
  #     define_method "of_#{name}" do |acid|
  #       acid.chars.map { |char| const_get(name.upcase)[char] }.join
  #     end
  #   end
  # end

  # version 2
  # def self.of_dna(dna)
  #   dna.chars.map { |char| char.extend(Map) }.map(&:dna).join
  # end

  # def self.of_rna(rna)
  #   rna.chars.map { |char| char.extend(Map) }.map(&:rna).join
  # end

  # module Map
  #   COMP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  #   def dna
  #    COMP.[self]
  #   end

  #   def rna
  #    COMP.key(self)
  #   end
  # end

end
