class Complement

  def self.of_dna(dna_strand)
    complimentary_strands(dna_strand, 'DNA')
  end

  def self.of_rna(rna_strand)
    complimentary_strands(rna_strand, 'RNA')
  end

  private

  def self.complimentary_strands(starting_strand, starting_strand_type)
    t_or_u, u_or_t = 0, 0

    if starting_strand_type == 'DNA'
      t_or_u = 'T'
      u_or_t = 'U'
    elsif starting_strand_type == 'RNA'
      t_or_u = 'U'
      u_or_t = 'T'
    end

    complimentary_strand = []
    starting_strand.chars.each do |nucleotide|
      if nucleotide == 'C'
        complimentary_strand << 'G'
      elsif nucleotide == 'G'
        complimentary_strand << 'C'
      elsif nucleotide == t_or_u
        complimentary_strand << 'A'
      elsif nucleotide == 'A'
        complimentary_strand << u_or_t 
      end
    end
    complimentary_strand.join
  end

end
