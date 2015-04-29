## 44 minutes to get these tests to all pass, ~55 minutes to clean
## This works by splitting a phrase into words, then splitting the words
#up into characters.  The characters are cleaned, then re-assembled.
#The words are cleaned, then re-assembled.  Finally we may the hash
#table to count frequencies.
#
require 'pry'
class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    split_phrase = split_phrase(@phrase)
    split_phrase.each_with_object(Hash.new(0)){|key,hash| hash[key] += 1}
  end

  def split_phrase(phrase)
    words = phrase.split(/ |,/)
    words = words.map{|word| clean_word(word)}
    words.reject(&:empty?)
  end

  def clean_word(word)
    letters = word.split('')
    letters = letters.select{|a| /\p{Lower}|\p{Upper}|[0-9]|'/.match(a)}
    word = letters.join().downcase
  end

end
