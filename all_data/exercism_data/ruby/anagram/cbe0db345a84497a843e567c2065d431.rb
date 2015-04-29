module Word
  def sort_characters(word)
    word.chars.sort.join
  end

  def normalize(word)
    word.downcase
  end
end

class Anagram
  include Word

  def initialize(detector)
    @normalized_detector = normalize(detector)
  end

  def match(potential_anagrams)
    potential_anagrams.select { |potential_anagram|
      normalized_anagram = normalize(potential_anagram)
      anagram?(sort_characters(normalized_anagram),sort_characters(@normalized_detector),normalized_anagram)
    }
  end

  private

  def anagram? (sorted_anagram, sorted_detector, normalized_anagram)
    sorted_anagram == sorted_detector && normalized_anagram != @normalized_detector
  end

end
