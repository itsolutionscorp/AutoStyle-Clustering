class DNA < String
  def to_rna
    tr("CGAT", "CGAU")  # Fore!
  end
end
