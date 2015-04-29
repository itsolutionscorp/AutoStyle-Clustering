class DNA < String
  def to_rna
    tr 'T', 'U'
  end
end
