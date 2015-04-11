class DNA

  def initialize(value)
    @value = value.upcase
  end

  def to_rna
    @value.gsub(/T/,'U')
  end

end
