class Complement
  DNA_TO_RNA = {'A' => 'U', 'T' => 'A', 'C' => 'G', 'G' => 'C'}
  RNA_TO_DNA = DNA_TO_RNA.invert
  CONVERSIONS = {'dna' => DNA_TO_RNA, 'rna' => RNA_TO_DNA}

  def self.method_missing(method, *args, &block)
    if method.to_s =~ /^of_(.+)$/
      nucleic_acid_type = method.to_s.match(/^of_(.+)$/)[1]
      strand = args[0].split("").inject("") do |strand, nucleotide|
        strand += complement(nucleic_acid_type, nucleotide)
      end
      return strand
    end
    super
  end

  private

  def self.complement(nucleic_acid_type, nucleotide)
    CONVERSIONS[nucleic_acid_type][nucleotide]
  end
end
