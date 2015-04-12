require 'contracts'
class Phrase
  include Contracts

  attr_reader :word_count

  Contract String => Any
  def initialize(sentence)
    @word_list = Tokenizer.tokenize(sentence)
  end

  Contract ArrayOf[String] => Hash
  def word_count
    @word_list.inject({}) do |words, word|
      word.downcase!
      words[word] = 0 if words[word].nil?
      words[word] += 1
      words
    end
  end
end

class Tokenizer
  include Contracts

  attr_reader :sentence

  Contract String => Any
  def self.tokenize(sentence)
    remove_extra_empties(split(clean(sentence)))
  end

  private

  Contract String => ArrayOf[String]
  def self.split(sentence)
    sentence.split(/[, ]/)
  end

  Contract String => String
  def self.clean(sentence)
    sentence.gsub(/[^a-zA-Z0-9' ,]/, '')
  end

  Contract ArrayOf[String] => ArrayOf[String]
  def self.remove_extra_empties(word_list)
    word_list.delete('')
    word_list
  end
end
