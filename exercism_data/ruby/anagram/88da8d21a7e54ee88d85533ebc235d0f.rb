class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select{|c| ComparesAnagrams.new(word) == ComparesAnagrams.new(c) }
  end
end

class ComparesAnagrams
  include Comparable
  
  attr_reader :word 

  def <=> other
    #not so sure about this if statement here; originally this was 
    #in Anagram#match.  It makes Anagram#match cleaner by moving logic here though.
    return if identical_to? other
    sorted_chars <=> other.sorted_chars
  end

  def initialize word
    @word = word.downcase
  end

  def sorted_chars
    @sorted_chars ||= word.each_char.sort
  end

  private
  def identical_to?(other)
    word == other.word
  end
end
