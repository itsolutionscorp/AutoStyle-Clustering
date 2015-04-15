class DNA < Struct.new(:sequence)

  def to_rna
    sequence.gsub('T', 'U')
  end

end
