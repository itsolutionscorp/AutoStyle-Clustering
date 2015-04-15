class Bob

  def hey(words)
    type = Phrase.new(words).determine_type
    return_response type
  end

  private

  def return_response(phrase_type)
    RESPONSES.fetch(phrase_type)
  end

  RESPONSES = {
    shout: 'Whoa, chill out!',
    question: 'Sure.',
    silence: 'Fine. Be that way!',
    other: 'Whatever.'
  }

end

class Phrase

  def initialize(words)
    @words = words
  end

  def determine_type
    if is_shout
      :shout
    elsif is_question
      :question
    elsif is_silence
      :silence
    else
      :other
    end
  end

  private

  def is_shout
    words.match(/[a-zA-Z]/) && words == words.upcase
  end

  def is_question
    words[-1, 1] == '?'
  end

  def is_silence
    words.strip == ""
  end

  attr_reader :words
end
