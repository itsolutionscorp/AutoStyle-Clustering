class DNA

  VALID_NUCLEOTIDES = %W[ A T C G U ]

  attr_reader :strand

  def initialize(strand)
    @strand = strand.strip.split("")
  end

  def count(nucleotide)
    unless VALID_NUCLEOTIDES.include? nucleotide
      raise ArgumentError, "invalid nucleotide: #{nucleotide}"
    end
    strand.count { |n| n == nucleotide }
  end

  def nucleotide_counts
    strand.each_with_object(counter) do |n, count|
      count[n] += 1
    end
  end

  private

    def counter
      default = {
        "A" => 0,
        "T" => 0,
        "G" => 0,
        "C" => 0
      }
      default.default_proc = ->(h, k) { h[k] = 0 }
      default
    end

end
