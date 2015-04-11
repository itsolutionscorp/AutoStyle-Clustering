class Anagram
  def initialize(word)
    @source = word.to_s
  end

  def match(array)
    array.select { |word| is_anagram? word.downcase }
  end

private

  def normalized_source
    @n_source ||= @source.downcase
  end

  def is_anagram?(word)
    normalized_source.chars.sort == word.chars.sort &&
      normalized_source != word
  end
end
