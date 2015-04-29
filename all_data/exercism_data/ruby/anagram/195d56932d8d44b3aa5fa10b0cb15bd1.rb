class Anagram
  def initialize(word)
    @chars = characters_histogram(word)
  end

  def match(possible_matches)
    possible_matches.select {|possible_match| anagram?(possible_match) }
  end

  private
    def anagram?(word)
      @chars == characters_histogram(word)
    end

    def characters_histogram(word)
      word.split("").inject({}) do |characters, char|
        characters[char] ||= 0
        characters[char] += 1
        characters
      end
    end
end
