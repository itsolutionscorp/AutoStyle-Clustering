class Anagram

  def initialize(input)
    @original = input.downcase
  end

  def match(words)
    words.find_all{|word|is_anagram?(word.downcase)}
  end

  def is_anagram?(input)
    same_count?(input) && different_word?(input)
  end

  def count(input)
    input.split("").each_with_object(Hash.new(0)) do |letter,hash|
      hash[letter] += 1
    end
  end

  def same_count?(input)
    count(@original)==count(input)
  end

  def different_word?(input)
    @original != input
  end

  private :is_anagram?, :count, :same_count?, :different_word?

end
