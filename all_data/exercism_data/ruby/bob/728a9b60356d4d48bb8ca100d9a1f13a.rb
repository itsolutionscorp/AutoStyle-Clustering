class Bob
  def hey(sentence)
    bobs_request = CallOut.new(sentence)
    Answer.new(bobs_request).reply
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
  def initialize(call_out)
    @call_out = call_out
  end

  def reply
    case
    when @call_out.loudly?
      "Woah, chill out!"
    when @call_out.with_a_question?
      "Sure."
    when @call_out.silently?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
