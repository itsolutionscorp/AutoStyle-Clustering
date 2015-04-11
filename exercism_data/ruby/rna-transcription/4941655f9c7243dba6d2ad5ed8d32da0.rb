class DNA < String

  def to_rna
    replace_thymidine_with_uracil
  end

  private

  def replace_thymidine_with_uracil
    gsub('T','U')
  end

end
