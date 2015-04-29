class Complement

  DNA = 'GCTA'
  RNA = 'CGAU'

  def self.of_dna(strand)
    transcript(strand, DNA, RNA)
  end

  def self.of_rna(strand)
    transcript(strand, RNA, DNA)
  end

  private
  def self.transcript(strand, from, to)
    raise ArgumentError if has_invalid_nucleotides_on?(strand, from)
    strand.tr(from, to)
  end

  def self.has_invalid_nucleotides_on?(strand, from)
    !strand.delete(from).empty?
  end

end
