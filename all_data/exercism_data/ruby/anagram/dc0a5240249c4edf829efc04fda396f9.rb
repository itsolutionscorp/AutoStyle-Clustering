class Anagram

  def initialize(word)
    @word = word.to_s
  end

  def match(word_list)
    word_list.delete_if {|word| !is_anagram?(word)}
  end

  private

  def is_anagram?(candidate)
    same_signature(candidate) && !same_word(candidate)
  end

  def same_signature(candidate)
    word_signature(candidate) == word_signature(@word)
  end

  def same_word(candidate)
    candidate.casecmp(@word).zero?
  end

  def word_signature(word)
    word.downcase.each_char.each_with_object(Hash.new(0)) do |char, count|
      count[char] += 1
    end
  end

end
