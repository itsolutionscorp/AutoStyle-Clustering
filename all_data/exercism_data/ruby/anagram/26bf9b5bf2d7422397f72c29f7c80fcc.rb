class Anagram
  def initialize(target)
    @target = target
    @target_char_count = char_count(@target.downcase)
  end

  def match(list)
    list.select { |word| is_anagram?(word.downcase) }
  end

  private

  def is_anagram?(word)
    !@target.eql?(word) && (char_count(word) == @target_char_count)
  end

  def char_count(word)
    word.chars.each_with_object(Hash.new(0)) { |char, count| count[char] += 1 }
  end
end
