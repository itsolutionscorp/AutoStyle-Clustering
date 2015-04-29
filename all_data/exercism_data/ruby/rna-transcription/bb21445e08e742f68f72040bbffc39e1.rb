class DNA
  DNA_TO_RNA_REPLACEMENT = { 'T' => 'U' }
  DNA_TO_RNA_REGEXP = Regexp.union(*DNA_TO_RNA_REPLACEMENT.keys)

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    # @strand.tr('T', 'U') # it's easier but less fun ;)
    @strand.gsub(DNA_TO_RNA_REGEXP, DNA_TO_RNA_REPLACEMENT)
  end
end
