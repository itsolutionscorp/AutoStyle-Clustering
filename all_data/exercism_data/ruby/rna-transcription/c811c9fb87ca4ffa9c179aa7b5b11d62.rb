class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  def self.of_dna(dna_strand)
    raise ArgumentError if dna_strand.include? 'U'
    convert(DNA_TO_RNA, dna_strand)
  end

  def self.of_rna(rna_strand)
    raise ArgumentError if rna_strand.include? 'T'
    convert(RNA_TO_DNA, rna_strand)
  end

  def self.convert(map, strand)
    complement_strand = ''

    strand.chars.each do |char|
      complement_strand << map[char]
    end

    complement_strand
  end
end
