class DNA < Struct.new(:strand)

  def to_rna
    replacements.each_with_object(strand.dup) do |(original, replacement), rna|
      rna.gsub!(original, replacement)
    end
  end

  private

  def replacements
    { "T" => "U" }
  end
end
