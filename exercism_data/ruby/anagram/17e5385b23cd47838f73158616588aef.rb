class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select { |word| is_anagram? word }
  end

  private

  def anagram
    @anagram ||= alphagram @word
  end

  def is_anagram?(word)
    return false if word.downcase == @word
    anagram == alphagram(word)
  end

  def alphagram(word)
    word.downcase.scan(/./).sort.join
  end
end
