class NucleicAcid
  BASES = %w{A C T G U}

  def self.is_valid?(nucleotide_list)
    invalid_bases = nucleotide_list.chars.none? do |base|
      !self::BASES.include?(base)
    end
  end
end

class RNA < NucleicAcid
  BASES = %w{A C T G U}
end

class DNA < NucleicAcid
  BASES = %w{A C T G}

  def initialize(nucleotides)
    @nucleotides = validate(sanitize(nucleotides))
  end

  def nucleotide_counts
    BASES.each_with_object(Hash.new(0)) do |base, tally|
      tally[base] = @nucleotides.count(base)
    end
  end

  def count(nucleotide)
    if !NucleicAcid::BASES.include?(nucleotide)
      raise ArgumentError, "Invalid nucleotide supplied to count; expected ACTG"
    else
      nucleotide_counts[nucleotide]
    end
  end

  private

  def sanitize(raw)
    raw.strip.upcase
  end

  def validate(raw)
    case 
    when RNA.is_valid?(raw) && !DNA.is_valid?(raw)
      raise ArgumentError, "RNA supplied; expecting DNA"
    when !DNA.is_valid?(raw)
      raise ArgumentError, "Invalid nucleotide found; ACTG only expected."
    else
      raw
    end
  end

end
