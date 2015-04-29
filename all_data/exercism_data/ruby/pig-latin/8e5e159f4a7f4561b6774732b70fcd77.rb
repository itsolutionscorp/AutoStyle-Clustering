require 'pry'
require 'forwardable'

class Sentence
  attr_reader :as_string
  def initialize(string)
    @as_string = string
  end

  def process_each_word(&block)
    to_a.collect { |word| yield(word) }.join(' ')
  end

  def to_a
    as_string.split(' ')
  end
end

class Word
  extend Forwardable
  def_delegator :@as_string, :[]
  def_delegator :@as_string, :to_s
  def_delegator :@as_string, :length
  attr_reader :as_string
  def initialize(as_string)
    @as_string = as_string
  end

  def first_vowel
    as_string[index_of_first_vowel]
  end

  def index_of_first_vowel
    as_string.index(/[aeiouy]/)
  end

  def letter_after_first(letter)
    index = as_string.index(letter)
    as_string[index+1]
  end

  def letter_before_first(letter)
    index = as_string.index(letter)
    as_string[index-1]
  end

  def letters_before_first(letter)
    index = as_string.index(letter)
    as_string[0...index]
  end

  def letters_before_first_vowel
    as_string.slice(0, index_of_first_vowel)
  end

  def starts_with?(letter)
    as_string.start_with?(*letter)
  end
end

class String
  def is_a_vowel?
    length == 1 && to_s.match(/[aeiouy]/)
  end
end

class PigLatin
  class << self
    def translate(englishString)
      sentence = Sentence.new(englishString)
      sentence.process_each_word do |word|
        translate_word(word)
      end
    end

    def translate_word(englishWord)
      word = Word.new(englishWord)

      if word.starts_with?(%w{ a e i o })
        "#{word}ay"
      elsif word.starts_with?('x') && !word.letter_after_first('x').is_a_vowel?
        word.to_s + 'ay'
      elsif word.first_vowel == 'y' && word.letter_after_first('y').is_a_vowel?
        word[1..-1] + 'yay'
      elsif word.first_vowel == 'u' && word.letter_before_first('u') == 'q'
        word[word.index_of_first_vowel+1..-1] + word.letters_before_first('q') + 'quay'
      else
        word[word.index_of_first_vowel..word.length] + word.letters_before_first_vowel + 'ay'
      end
    end
  end
end
