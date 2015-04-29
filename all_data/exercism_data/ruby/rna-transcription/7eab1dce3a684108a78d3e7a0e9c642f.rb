class DNA < String

  def to_rna
    gsub('T','U')
  end

end
