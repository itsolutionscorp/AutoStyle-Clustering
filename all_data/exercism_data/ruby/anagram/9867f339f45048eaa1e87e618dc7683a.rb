class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.delete_if { |word| word.downcase == @word }.select { |word| is_anagram? word }
  end

  private

  def anagram
    @anagram ||= alphagram @word
  end

  def is_anagram?(word)
    anagram == alphagram(word)
  end

  def alphagram(word)
    word.downcase.scan(/./).sort.join
  end
end
