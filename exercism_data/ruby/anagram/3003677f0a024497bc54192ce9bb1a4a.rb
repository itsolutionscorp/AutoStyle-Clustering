module NU
  class Word
    attr_reader :text

    def self.letter_frequency(text)
      text.chars.each.inject(Hash.new(0)) do |hash, letter|
        hash[letter.downcase] += 1
        hash
      end
    end

    def initialize(text)
      @text = text
    end

    def anagram?(word)
      letter_frequency == self.class.letter_frequency(word)
    end

    def exact_match?(word)
      word.downcase == text.downcase
    end

    def anagrams_from_list(list)
      list.select { |word| anagram?(word) && !exact_match?(word) }
    end

    def letter_frequency
      self.class.letter_frequency(text) 
    end
  end
end

# This is a little adapter to fit the requirements of the testing
# interface.  Since the new Word class might be part of a bigger
# library, or even a gem that's been factored out of the program
# it might make sense to make use of an adapter like this
class Anagram
  attr_reader :word

  def initialize(word)
    @word = NU::Word.new(word)
  end

  def match(words)
    word.anagrams_from_list(words)
  end
end

# The new shiny class always comes with some tests of its own
#require 'minitest/spec'
#require 'minitest/autorun'

#require_relative 'anagram'

#def test_word
  #"listen"
#end

#def test_anagram
  #"inlets"
#end

#def test_frequency
  #{"l"=>1, "i"=>1, "s"=>1, "t"=>1, "e"=>1, "n"=>1}
#end

#def test_not_an_anagram
  #"cucumber"
#end

#def test_list
  #[test_anagram, test_not_an_anagram]
#end

#def test_no_agagram_list
  #[test_not_an_anagram]
#end

#def test_anagrams
  #[test_anagram]
#end

#def subject
  #NU::Word.new(test_word)
#end

#describe NU::Word do
  #describe 'initialization' do
    #it 'stores the value as text' do
      #subject.text.must_equal test_word
    #end
  #end

  #describe 'anagram?' do
    #it 'takes a single word and returns true if the word is an anagram' do
      #subject.anagram?(test_anagram).must_equal true
      #subject.anagram?(test_not_an_anagram).must_equal false
    #end
  #end

  #describe 'anagrams_from_list' do
    #it 'returns the set of words that are correct anagrams' do
      #subject.anagrams_from_list(test_list).must_equal test_anagrams
    #end
    #it 'returns an empty list when there are no correct anagrams' do
      #subject.anagrams_from_list(test_no_agagram_list).must_equal []
    #end
  #end

  #describe 'letter_frequency' do
    #it 'returns the letter frequency for the word the class represents' do
      #subject.letter_frequency.must_equal test_frequency
    #end
  #end

  #describe '.letter_frequency' do
    #it 'returns a hash of the frequency that letters occur' do
      #NU::Word.letter_frequency(test_anagram).must_equal(test_frequency)
    #end
  #end
#end
