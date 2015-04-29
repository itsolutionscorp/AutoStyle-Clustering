class Anagram

  def initialize(detector)
    @normalized_detector = normalize(detector)
  end

  def match(potential_anagrams)
    anagrams = []
    index = 0

    potential_anagrams.each do |potential_anagram|
      @normalized_anagram = normalize(potential_anagram)

      if anagram?(
          sort_characters_in_word(@normalized_anagram),
          sort_characters_in_word(@normalized_detector),
          @normalized_anagram,
          @normalized_detector )
        anagrams[index] = potential_anagram
        index += 1
      end
    end
    anagrams
  end

  private

  def anagram? (sorted_anagram, sorted_detector, normalized_anagram, normalized_detector)
    sorted_anagram == sorted_detector && does_not_equal?(normalized_anagram, normalized_detector)
  end

  def does_not_equal?(anagram, detector)
    anagram != detector
  end

  def sort_characters_in_word(word)
    word.chars.sort.join
  end

  def normalize(word)
    word.downcase
  end
end
