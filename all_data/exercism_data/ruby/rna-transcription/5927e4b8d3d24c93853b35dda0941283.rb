class DNA < Struct.new(:strand)

  def to_rna
    strand.gsub /T/, 'U'
  end

end
