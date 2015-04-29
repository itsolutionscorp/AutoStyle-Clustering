class DNA
  DNA_NUCLEOTIDES = %w[A T C G]
  RNA_NUCLEOTIDES = %w[A C G U]
  ALL_NUCLEOTIDES = (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq

  def initialize(dna_string)
    self.dna_string = dna_string
    validate! dna_string, DNA_NUCLEOTIDES
  end

  def count(char)
    validate! char, ALL_NUCLEOTIDES
    nucleotide_counts[char]
  end

  def nucleotide_counts
    @nucleotide_counts ||=
      dna_string
        .each_char
        .with_object(initial_counts) { |nucleotide, counts|
          counts[nucleotide] += 1
        }
  end


  private

  attr_accessor :dna_string

  def initial_counts
    DNA_NUCLEOTIDES.each_with_object Hash.new(0) do |nucleotide, counts|
      counts[nucleotide] = 0
    end
  end

  def validate!(nucleotide_string, allowed_nucleotides)
    return if nucleotide_string =~ /^(?:#{allowed_nucleotides.join '|'})*$/
    raise ArgumentError, "#{nucleotide_string.inspect} contains nucleotides not in #{allowed_nucleotides.inspect}"
  end
end
