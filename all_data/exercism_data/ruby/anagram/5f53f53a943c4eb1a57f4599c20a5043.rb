def signature_for(word)
  word.split("").group_by {|w| w}
end

class Anagram
  def initialize(word)
    @word = word.downcase
    @signature = signature_for(@word)
  end

  def is_anagram(word)
    signature_for(word) == @signature && word != @word
  end

  def match(potential_anagrams)
    potential_anagrams.select {|word|
      is_anagram(word.downcase)
    }
  end
end
