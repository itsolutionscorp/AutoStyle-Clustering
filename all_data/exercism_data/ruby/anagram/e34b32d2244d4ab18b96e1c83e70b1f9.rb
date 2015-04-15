class Word

  attr_reader :value

  def initialize(word)
    @value = word.downcase
  end

  def signature
    @signature ||= @value.scan(/./).each_with_object(Hash.new(0)) do|letter, manifest|
      manifest[letter] += 1
    end.sort
  end

  def anagram?(subject)
    value != subject.value && signature == subject.signature
  end

end

class Anagram

  # Source: http://en.wikipedia.org/wiki/Anagram
  # The original word or phrase is known as the subject of the anagram
  def initialize(word)
    @subject = Word.new(word)
  end

  def match(word_list)
    word_list.select! do |word|
      Word.new(word).anagram? @subject
    end
  end

end
