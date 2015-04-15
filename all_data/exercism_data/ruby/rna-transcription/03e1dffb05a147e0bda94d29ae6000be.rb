class DNA < Struct.new(:strand)

  def to_rna
    strand.gsub(chars_to_replace, replacements)
  end

  private

  def replacements
    { "T" => "U" }
  end

  def chars_to_replace
    /(#{replacements.keys.join "|"})/
  end
end
