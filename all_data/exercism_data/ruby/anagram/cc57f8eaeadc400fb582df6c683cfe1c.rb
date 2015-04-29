require 'pry'
class Anagram
  attr_accessor :word

  def initialize word
    @anagrams = generate_anagram(word.downcase)[1..-1]
  end

  def match anagrams
    result = @anagrams & anagrams
    result.empty? ? match_with_capitalize?(anagrams) : result
  end

  private

  def generate_anagram word
    word.chars.permutation(word.length).map(&:join)
  end

  def match_with_capitalize? anagrams
    @anagrams.map(&:capitalize) & anagrams
  end
end
