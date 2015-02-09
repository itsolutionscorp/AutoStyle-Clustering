require 'test/unit'

# Part 3

def sorted_word(word)
  word.downcase.split(//).sort.join('')
end

def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    key = sorted_word(word)
    anagrams[key] ||= []
    anagrams[key] << word
  end
  anagrams.values
end

class HW1Part3Tests < Test::Unit::TestCase
  def test_combine_anagrams
    assert_equal [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort,
      combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).sort
  end
end
