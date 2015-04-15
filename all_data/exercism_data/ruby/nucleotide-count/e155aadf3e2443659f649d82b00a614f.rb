class DNA
  BASE = %w{ A C G T }

  def initialize(dna)
    validate dna, "Invalid sequence '#{dna}'"
    @dna = dna
  end

  def count(nucleo)
    validate nucleo, "Invalid nucleotide '#{nucleo}'", 'U'
    @dna.chars.count nucleo
  end

  def nucleotide_counts
    BASE.each_with_object({}) { |char, counts| counts[char] = count char }
  end

  private

  def validate(arg, error, extra=nil)
    raise ArgumentError, error unless
    arg.chars.all? { |c| BASE.dup.push(extra).include? c }
  end
end
