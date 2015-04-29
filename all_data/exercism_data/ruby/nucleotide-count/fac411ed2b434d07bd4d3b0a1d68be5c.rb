class DNA

  attr_reader :nucleotide_counts

  def initialize(dna_string)
    @dna_string = dna_string 

    if invalid_dna_string?
      raise ArgumentError.new('Invalid DNA string input')
    end

    @nucleotide_counts = create_nucleotide_count
  end

  def count(nucleotide)
    unless valid_nucleotide?(nucleotide)
      raise ArgumentError.new("Invalid nucleotide search at #{nucleotide}")
    end

    nucleotide_counts[nucleotide] || 0
  end

private

  def invalid_dna_string?
    !@dna_string.empty? && /^[ATCG]+$/.match(@dna_string).nil?
  end

  def valid_nucleotide?(nucleotide)
    /[UATCG]/.match(nucleotide)
  end

  def create_nucleotide_count 
    dna_count = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    @dna_string.chars.each do |nucleotide|
      nucleotide.upcase!
      dna_count[nucleotide] += 1
    end
    dna_count
  end

end
