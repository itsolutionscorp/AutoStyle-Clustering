class Complement

  DNA_TO_RNA = { 
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    transcript(strand, DNA_TO_RNA)
  end

  def self.of_rna(strand)
    transcript(strand, RNA_TO_DNA)
  end

  private
  def self.transcript(strand, to)
    begin
      new_strand = ''
      strand.each_char { |c| new_strand += to.fetch(c) }
      new_strand
    rescue KeyError => e
      raise ArgumentError
    end
  end

end
