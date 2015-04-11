class Anagram
  def initialize(word)
    @letters = breaks word
  end

  def match(words)
    words.each_with_object([]) do |word, match|
      if @letters == breaks(word)
        match << word
      end
    end
  end

  private

  def breaks(word)
    word.to_s.downcase.split('').sort
  end
end
