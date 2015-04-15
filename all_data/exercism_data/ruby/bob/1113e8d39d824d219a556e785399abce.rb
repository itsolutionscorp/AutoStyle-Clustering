class Sentence
  def initialize(words)
    @words = words
  end

  def self.of(words)
    Sentence.new(words)
  end

  def is_shouted?
    is_something_other_than_numbers? && is_all_upper_case?
  end

  def is_a_question?
    words.end_with? '?'
  end

  def is_silent?
    words.strip.empty?
  end

  private

  attr_reader :words

  def is_something_other_than_numbers?
    words =~ /[A-Za-z]/
  end

  def is_all_upper_case?
    words == words.upcase
  end
end

class Response
  def initialize(sentence_in_response_to)
    @sentence = sentence_in_response_to
  end

  def self.to(sentence)
    Response.new(sentence)
  end

  def say
    return 'Woah, chill out!' if sentence.is_shouted?
    return 'Sure.' if sentence.is_a_question?
    return 'Fine. Be that way!' if sentence.is_silent?
    'Whatever.'
  end

  private

  attr_reader :sentence
end

class Bob
  def hey(something)
    Response.to(Sentence.of(something)).say
  end
end
