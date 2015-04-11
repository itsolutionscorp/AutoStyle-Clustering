class Anagram

  def initialize(word)
    @word = word.to_s
    @word_signature = word_signature(@word)
  end

  def match(word_list)
    word_list.select {|word| is_anagram?(word)}
  end

  private

  def is_anagram?(candidate)
    same_signature?(candidate) && !same_word?(candidate)
  end

  def same_signature?(candidate)
    word_signature(candidate) == @word_signature
  end

  def same_word?(candidate)
    candidate.casecmp(@word).zero?
  end

  def word_signature(word)
    word.downcase.chars.sort.join
  end

end
