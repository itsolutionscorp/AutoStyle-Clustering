class Bob
  def initialize
    @interactions = [Shout, Question, Blank, General]
  end

  def hey(message)
    find_interaction(message).reply
  end

  private

  def find_interaction(message)
    @interactions.map { |interaction| interaction.understands?(message) }.first
  end
end

class BobsVocabulary
  attr_reader :reply

  def self.understands?(message)
    MessageParser.new(message).parse
  end
end

class Shout < BobsVocabulary
  def initialize
    @reply = 'Woah, chill out!'
  end
end

class Question < BobsVocabulary
  def initialize
    @reply = 'Sure.'
  end
end

class Blank < BobsVocabulary
  def initialize
    @reply = 'Fine. Be that way!'
  end
end

class General < BobsVocabulary
  def initialize
    @reply = 'Whatever.'
  end
end


class MessageParser
  def initialize(body)
    @body = body
  end

  def parse
    shout || question || blank || general
  end

  private

  def shout
    Shout.new if upcased? && has_characters?
  end

  def question
    Question.new if last_character_is_question_mark?
  end

  def blank
    Blank.new if empty?
  end

  def general
    General.new
  end

  def upcased?
    @body == @body.upcase
  end

  def has_characters?
    @body =~ /[a-z]/i
  end

  def last_character_is_question_mark?
    @body[-1,1] == '?'
  end

  def empty?
    @body.strip.empty?
  end
end
