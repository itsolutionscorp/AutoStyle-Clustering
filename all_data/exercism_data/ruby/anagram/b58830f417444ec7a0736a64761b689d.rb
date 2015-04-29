class Anagram
  def initialize base
    @base = base
  end

  def match lexicon
    lexicon.select { |word| anagram? word }
  end

  private

  attr_reader :base

  def anagram? word
    normalized_word = word.downcase

    normalized_word != normalized_base &&
      sort(normalized_word) == sorted_base
  end

  def normalized_base
    @normalized_base ||= base.downcase
  end

  def sorted_base
    @sorted_base ||= sort(normalized_base)
  end

  def sort word
    word.chars.sort.join
  end
end
