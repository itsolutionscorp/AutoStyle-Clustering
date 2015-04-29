class DNA
  def initialize(strain)
    @strain = strain
  end

  def to_rna
    @strain.gsub(/T/,'U')
  end
end
