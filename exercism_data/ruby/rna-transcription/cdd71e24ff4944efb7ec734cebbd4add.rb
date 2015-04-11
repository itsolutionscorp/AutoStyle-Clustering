class Complement
  DNA = 'ACGT'
  RNA = 'UGCA'

  def self.of_dna(strand)
    map_strand(strand, DNA, RNA)
  end

  def self.of_rna(strand)
    map_strand(strand, RNA, DNA)
  end

  private

  def self.map_strand(strand, from, to)
    strand.chars.map{ |c| to[from.index(c)] }.join
  end

end
