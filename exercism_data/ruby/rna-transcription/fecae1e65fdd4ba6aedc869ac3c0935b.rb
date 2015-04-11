class DNA < String
  def to_rna
    tr("T", "U")  # Fore!
  end
end
