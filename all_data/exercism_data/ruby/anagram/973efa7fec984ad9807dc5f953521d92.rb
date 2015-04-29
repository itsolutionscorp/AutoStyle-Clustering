class Anagram
  include Comparable

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.reject{|c| identical_to? c}\
              .select{|c| anagram_of? c}
  end

  def downcased
    word.downcase
  end

  def sorted_chars
    @sorted_chars ||= downcased.each_char.sort
  end

  def <=> other
    sorted_chars <=> other.sorted_chars
  end

  private
  def identical_to?(other)
    downcased == Anagram.new(other).downcased
  end

  def anagram_of? candidate
    self == Anagram.new(candidate)
  end
end
