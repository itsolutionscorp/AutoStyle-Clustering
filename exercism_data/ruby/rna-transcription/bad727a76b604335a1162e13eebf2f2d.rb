class Complement
  COMPLEMENTS = { dna: { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' },
                  rna: { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' } }

  class << self
    [:dna, :rna].each do |acid|
      define_method("of_#{acid}") do |strand|
        strand.chars.map { |nucleotide| COMPLEMENTS[acid][nucleotide] }.join
      end
    end
  end
end
