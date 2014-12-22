class String
  include Enumerable

  def each
    self.each_char do |char|
      yield char
    end
  end
end

# Group words if they are anagrams
#
# @param words [Array<String>] words to group
# @return [Array] grouped words
def combine_anagrams(words)
  grouped_words = []
  words.each do |word|
    current_anagram_mask = word.downcase.sort
    anagram_masks = grouped_words.map { |elt| elt[0].downcase.sort }
    if anagram_masks.include? current_anagram_mask
      grouped_words[anagram_masks.index current_anagram_mask] << word
    else
      grouped_words << [word]
    end
  end
  grouped_words
end