class Anagram

  def initialize(word_to_watch)
    @word_to_match = word_to_watch
    @anagram_score = word_score(@word_to_match)
  end

  def match(possible_matches)
    matching_anagrams = Array.new
    possible_matches.each do |word|
      next if same_as_anagram(word)
      matching_anagrams.push(word) if word_score(word) == @anagram_score
    end
    matching_anagrams
  end

private

  def word_score(word)
    word_hash = Hash.new(0)
    word.split(//).each do |letter|
      word_hash[letter.downcase] += 1
    end
    word_hash
  end

  def same_as_anagram(word)
    word.casecmp(@word_to_match) == 0
  end

end
