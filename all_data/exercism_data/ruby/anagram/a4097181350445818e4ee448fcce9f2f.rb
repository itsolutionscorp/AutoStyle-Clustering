class Anagram
  def initialize(phrase)
    @phrase = phrase
  end

  def match(word_list)
    anagrams = create_anagrams
    anagrams = delete_if_same_word!(anagrams)
    create_intersection(anagrams, word_list)
  end

  private

  def create_anagrams
    @phrase.chars.permutation.to_a.each_with_object([]) do |w, a|
      unless same_word?(w) 
        a << w.join
        a << w.join.capitalize
      end
    end
  end

  def same_word?(word)
    word == @phrase || word == @phrase.capitalize
  end

  def create_intersection(a1, a2)
    a1.to_a & a2.to_a
  end
end
