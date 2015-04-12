require 'active_support'
require 'active_support/core_ext'

class Phrase
  NOT_A_WORD_CHAR = /[^'[:word:]]/

  attr_reader :word_count

  def initialize(phrase)
    fail ArgumentError, 'Argument given is not a string', caller if phrase.class != String
    @word_count = get_word_count(phrase)
    fail ArgumentError, 'String given has no words', caller if @word_count == {}
  end

  private

  def get_word_count(str)
    @phrase_metadata = {}
    @all_words = clean_string(str).split
    @unique_words = @all_words.uniq

    @unique_words.each do |unique_word|
      @phrase_metadata[unique_word] = @all_words.count { |word| word == unique_word }
    end

    @phrase_metadata
  end

  def clean_string(str)
    str.gsub(NOT_A_WORD_CHAR, ' ').squish.downcase
  end
end
