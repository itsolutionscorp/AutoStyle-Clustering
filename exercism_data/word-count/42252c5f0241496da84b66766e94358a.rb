class Phrase < String

  def word_count
    Hash[word_count_as_array]
  end

  def word_count_as_array
    to_a.uniq.collect {|word| [word, to_a.count(word)]}
  end

  def to_a
    normalize.split
  end

  def normalize
    downcase.allowed_characters.commas_to_spaces
  end

  def allowed_characters
    gsub(/[^0-9A-Za-z\s\,]/, "")
  end

  def commas_to_spaces
    gsub(",", " ")
  end

end
