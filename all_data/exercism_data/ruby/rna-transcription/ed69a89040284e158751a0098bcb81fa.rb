class DNA
  DNA_STRING_VAR = 'T'
  RNA_STRING_VAR = 'U'

  def initialize(strain)
    @strain = strain
  end

  def to_rna
    @strain.tr( DNA_STRING_VAR, RNA_STRING_VAR)
  end
end
