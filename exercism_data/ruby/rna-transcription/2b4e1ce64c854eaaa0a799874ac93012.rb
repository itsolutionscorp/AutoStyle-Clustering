class DNA < String
  def to_rna
    thymidine_to_uracil
  end

  private
  def thymidine_to_uracil
    tr('T', 'U')
  end
end
