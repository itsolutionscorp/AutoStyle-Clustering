class Complement
  @complementor = Complement.new
  def self.of_dna(strand)
    @complementor.make_complement(strand, true)
  end

  def self.of_rna(strand)
    @complementor.make_complement(strand)
  end

  def make_complement(strand, of_dna = false)
    complement_strand = ""
    strand.chars.each do |nucleotides|
      case nucleotides
      when 'C'
        complement_strand << "G"
      when 'G'
        complement_strand << "C"
      when 'T', 'U'
        complement_strand << "A"
      when 'A'
        if of_dna
          complement_strand << "U"
        else
          complement_strand << "T"
        end
      end
    end
    complement_strand
  end
end
