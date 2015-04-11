class Complement
  DNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(strand)
    new(strand).calculate(DNA_MAPPING)
  end

  def self.of_rna(strand)
    new(strand).calculate(RNA_MAPPING)
  end

  def initialize(strand)
    @strand = strand
  end

  def calculate(mapping)
    @strand.
      scan(/\w/).
      map { |n|
        mapping.fetch(n.upcase) do
          raise "#{n.upcase} is not valid nucleotide"
        end
      }.
      join('')
  end
end
