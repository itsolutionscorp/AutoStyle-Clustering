class DNA < Struct.new(:sequence)

  def to_rna
    sequence.tr 'T', 'U'
  end

end
