class Bob

  def hey(words)
    @phrase = Phrase.new(words)
    RESPONSES.find do |ary|
      ary[0].call(@phrase)
    end.fetch(1)
  end

  private

  RESPONSES = {
    lambda { |phrase| phrase.is_shout } => 'Whoa, chill out!',
    lambda { |phrase| phrase.is_question } => 'Sure.',
    lambda { |phrase| phrase.is_silence } => 'Fine. Be that way!',
    lambda { |phrase| phrase.is_other } => 'Whatever.'
  }

end

class Phrase

  def initialize(words)
    @words = words
  end

  def is_shout
    words.match(/[a-zA-Z]/) && words == words.upcase
  end

  def is_question
    words[-1, 1] == '?'
  end

  def is_silence
    words.strip == ""
  end

  def is_other
    !is_shout && !is_question && !is_silence
  end

  attr_reader :words

end
