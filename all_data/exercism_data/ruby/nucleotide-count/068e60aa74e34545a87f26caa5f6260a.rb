class DNA
  attr_reader :nucleotide_counts

  DNA = ["A", "C", "G", "T"]

  def initialize( dna_string )
    raise ArgumentError.new("Invalid DNA") unless valid_dna_string?( dna_string )
    @dna_string         = dna_string
    @nucleotide_counts  = { 'A' => 0,
                            'C' => 0,
                            'G' => 0,
                            'T' => 0}

    count_dna_nucleotides                            
  end

  def count_dna_nucleotides
    DNA.each do |nucleotide|
      @nucleotide_counts[ nucleotide ] = @dna_string.chars.count( nucleotide )
    end
  end

  def count( nucleotide )
    if nucleotide.length == 1 
      if valid_nucleotide?( nucleotide , "DNA")
        @nucleotide_counts[ nucleotide ]
      elsif valid_nucleotide?( nucleotide , "RNA" )
        0
      else
        raise ArgumentError.new("Count argument must be a valid DNA or RNA nucleotide")
      end
    else
      raise ArgumentError.new("Count argument must be a valid DNA or RNA nucleotide")
    end
  end

  def valid_dna_string?( dna_string )
    if dna_string.empty?
      true
    else
      valid_dna = true
      dna_string.chars.each do |letter|
        valid_dna = false if !valid_nucleotide?( letter, "DNA")
      end
      valid_dna
    end
  end

  def valid_nucleotide?( nucleotide, type="" )
    case type.downcase
    when "dna"  then reg = /[^ACGT]/
    when "rna"  then reg = /[^ACGU]/
    else             reg = /[^ACGTU]/
    end
    nucleotide[reg].nil? 
  end
end
