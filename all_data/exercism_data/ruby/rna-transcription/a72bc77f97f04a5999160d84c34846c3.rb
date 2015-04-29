class DNA
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.chars.map do |char|
      RNA_MAPPING[char.to_sym]
    end.join
  end

  private

  RNA_MAPPING = {
    :A => 'A',
    :C => 'C',
    :G => 'G',
    :T => 'U',
  }
end
