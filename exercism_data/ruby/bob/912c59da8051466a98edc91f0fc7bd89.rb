class Bob

  def hey(given_sentence)

    sentence_type = Sentence.new(given_sentence).type

    case sentence_type
    when LoudSentence
      "Woah, chill out!"
    when Question
      "Sure."
    when InaudibleSentence
      "Fine. Be that way!"
    else
      "Whatever."
    end

  end

end

class Sentence

  def initialize(sentence)
    @sentence = sentence.to_s.strip
  end

  def type
    case
    when shout?
      LoudSentence.new
    when question?
      Question.new
    when silent?
      InaudibleSentence.new
    end
  end

  private

  def shout?
    !silent? && @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end

  def silent?
    @sentence.empty?
  end

end

LoudSentence = Class.new
Question = Class.new
InaudibleSentence = Class.new
