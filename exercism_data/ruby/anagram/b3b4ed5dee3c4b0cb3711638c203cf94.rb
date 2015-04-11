class Anagram
  def initialize(word)
    @original_word = word
    @sorted_letters = sort_letters(word)
  end

  def match(candidates)
    candidates.select{ |w| anagram?(w) }
  end

  private

  def anagram?(candidate)
    return false if @original_word == candidate
    return false if @original_word.size != candidate.size
    return false unless casing_matches?(candidate)

    @sorted_letters == sort_letters(candidate)
  end

  def casing_matches?(candidate)
    candidate.chars.zip(@original_word.chars).all? { |c1, c2|
      character_case(c1) == character_case(c2)
    }
  end

  def character_case(char)
    case char
    when 'a'..'z' then :lower
    when 'A'..'Z' then :upper
    else :none
    end
  end

  def sort_letters(word)
    word.downcase.chars.sort.join
  end
end
