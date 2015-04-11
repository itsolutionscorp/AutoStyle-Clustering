class Bob
  def hey(sentence)
    bobs_request = CallOut.create(sentence)
    Answer.new(bobs_request).reply
  end
end

class CallOut
  def initialize(written_request)
    @written_request = written_request
  end

  def self.create(written_request)
    written_request = written_request.to_s.strip
    if written_request.empty?
      NullCallOut.new
    else
      new(written_request)
    end
  end
  private_class_method :new # force instantiation via CallOut.create

  def loudly?
    @written_request == @written_request.upcase
  end

  def with_a_question?
    # should really be:
    # !loudly? && @written_request.end_with?("?")
    @written_request.end_with?("?")
  end

  def silently?
    false
  end
end

class NullCallOut

  def loudly?
    false
  end

  def with_a_question?
    false
  end

  def silently?
    true
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
