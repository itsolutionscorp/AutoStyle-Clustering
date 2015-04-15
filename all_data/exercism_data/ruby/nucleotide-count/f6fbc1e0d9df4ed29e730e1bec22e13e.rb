class DNA

  attr_reader :nucleotides

  def initialize(string)
    @nucleotides = string.chars
    @nucleotides.each {|nucleotide| validate(nucleotide)}
  end

  def count(nucleotide)
    validate(nucleotide)
    @nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'G' => count('G'),
      'C' => count('C')
    }
  end

  def validate(nucleotide)  
      raise ArgumentError.new("#{nucleotide} is not a nucleotide.") if !valid?(nucleotide)
    end

  def valid?(nucleotide)
    %w(A T C G U).include?(nucleotide)
  end

end
