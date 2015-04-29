class DNA
  def initialize(string)
    @string = string
    validate_string
  end

  def count(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a nucleotide" unless valid_nucleotide?(nucleotide)
    @string.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  private

  def nucleotides
    ['A', 'T', 'C', 'G']
  end

  def validate_string
    @string.each_char { |char| raise ArgumentError, "Invalid string" unless valid_nucleotide?(char) }
  end

  def valid_nucleotide?(char)
    nucleotides.include?(char)
  end
end
