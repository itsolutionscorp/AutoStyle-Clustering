class DEOXYRIBONECLEIC
  attr_reader :nucleotide_counts

  def initialize(strand)
    @strands = strand.chars
    @molecules = %w{A C G T U}

    @strands.each do |s|
      valid_strand s, @exclude, "Invalid nucleotide argument with #{@strands}"
    end

    @nucleotide_counts = @molecules.each_with_object(Hash.new()) do |m, values|
      if m != @exclude
        values[m] ||= 0
        values[m] += @strands.count(m)
      end
    end
  end

  def count(strand)
    valid_strand strand

    # return strand count or if not valid strand for acid type return 0
    @nucleotide_counts[strand] || 0
  end

  def valid_strand(molecule, exclude = "", error = "Invalid strand")
    unless @molecules.include?(molecule) && molecule != exclude
      raise ArgumentError, error
    end
  end
end

class DNA < DEOXYRIBONECLEIC
  def initialize(strand)
    @exclude = "U"
    super
  end

end

class RNA < DEOXYRIBONECLEIC
  def initialize(strand)
    @exclude = "T"
    super
  end
end
