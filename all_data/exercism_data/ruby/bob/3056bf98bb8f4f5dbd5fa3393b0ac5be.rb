class Bob
  def hey(sentence)
    bobs_request = CallOut.new(sentence)
    Answer.new.reply_to(bobs_request)
  end
end

class CallOut
  def initialize(written_request)
    @written_request = written_request.to_s.strip
  end

  def loudly?
    !silently? && @written_request == @written_request.upcase
  end

  def with_a_question?
    @written_request.end_with? "?"
  end

  def silently?
    @written_request.empty?
  end
end

class Answer
  def reply_to(asked)
    case
    when asked.loudly?
      "Woah, chill out!"
    when asked.with_a_question?
      "Sure."
    when asked.silently?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
