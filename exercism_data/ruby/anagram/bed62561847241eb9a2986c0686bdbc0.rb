class Anagram
  def initialize(word)
    @letters = break_in_letters word
  end

  def match(words)
    words.each_with_object([]) do |word, matches|
      if @letters == break_in_letters(word)
        matches << word
      end
    end
  end

  private

  def break_in_letters(word)
    word.to_s.downcase.chars.sort
  end
end
