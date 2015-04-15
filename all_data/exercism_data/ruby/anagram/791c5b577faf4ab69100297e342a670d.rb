class Anagram
  def initialize(word)
    @word = word
  end

  def match(other_words)
    other_words.reject { |other| Word.equal?(@word, other) }
               .select { |other| Word.anagrams?(@word, other) }
  end
end

module Word
  def self.equal?(a, b)
    a.casecmp(b).zero?
  end

  def self.anagrams?(a, b)
    equal?(sort(a), sort(b))
  end

  def self.sort(word)
    word.chars.sort_by(&:downcase).join
  end
end
