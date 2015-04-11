class Anagram
  def initialize(target)
    @target = target
    @target_breakdown = character_breakdown(@target.downcase)
  end

  def match(list)
    list.select { |word| anagram?(word.downcase) }
  end

  private

  def anagram?(word)
    !@target.eql?(word) && (character_breakdown(word) == @target_breakdown)
  end

  def character_breakdown(word)
    word.chars.each_with_object(Hash.new(0)) { |char, count| count[char] += 1 }
  end
end
