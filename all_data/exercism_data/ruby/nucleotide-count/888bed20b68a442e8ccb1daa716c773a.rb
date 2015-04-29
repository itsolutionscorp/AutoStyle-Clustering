class DNA
  attr_reader :string
  
  def initialize(string)
    @string = string.chars
    @string.each do |nucleotide|
      validate(nucleotide)
    end
  end

  def count(letter)
    validate(letter)
    matches = string.find_all do |nucleotide|
      nucleotide == letter
    end
    matches.length
  end

  def nucleotide_counts
    valid_nucleotides.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def validate(letter)
    raise ArgumentError unless valid_nucleotides.include?(letter)
  end

  def valid_nucleotides
    valid_nucleotides = ['A', 'T', 'C', 'G']
  end
end
