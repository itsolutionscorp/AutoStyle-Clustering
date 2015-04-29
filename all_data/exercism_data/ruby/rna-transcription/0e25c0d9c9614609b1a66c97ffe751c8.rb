class DNA < Struct.new(:code)
  RNA_DICTIONARY = {
    "A" => "A",
    "C" => "C",
    "G" => "G",
    "T" => "U"
  }

  def to_rna
    dictionary = unique_dictionary(RNA_DICTIONARY)
    code.tr(dictionary.keys.join, dictionary.values.join)
  end

  private

  def unique_dictionary(dictionary)
    dictionary.reject do |from, to|
      from == to
    end
  end
end
