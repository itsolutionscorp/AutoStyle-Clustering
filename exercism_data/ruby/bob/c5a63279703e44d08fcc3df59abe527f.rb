class Bob

  def hey(given_sentence)

    sentence = Sentence.new(given_sentence)

    # case sentence
    # when :loud?
    #   "Woah, chill out!"
    # when :a_question?
    #   "Sure."
    # when :inaudible?
    #   "Fine. Be that way!"
    # else
    #   "Whatever."
    # end

    # Same as if sentence.=== :loud?
    if sentence.is :loud?
      "Woah, chill out!"
    elsif sentence.is :a_question?
      "Sure."
    elsif sentence.is :inaudible?
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

  def shout?
    !silent? && @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end

  def silent?
    @sentence.empty?
  end

  def ===(intent)
    case intent
    when :loud?
      shout?
    when :a_question?
      question?
    when :inaudible?
      silent?
    end
  end
  alias_method :is, :===

end
