class Bob
  # "LOUD QUESTION?" can evaluate to either `loud` or `question`
  # under ruby 2.0-p247 it chooses `loud`
  CANNED_REPLIES = {
    loud:     "Woah, chill out!",
    silence:  "Fine. Be that way!",
    question: "Sure."
  }
  DEFAULT_REPLY = "Whatever."

  def canned_reply_for(remark)
    (canned = CANNED_REPLIES.find { |k,v| v if remark.send("#{k}?") }) && canned.last
  end

  def hey(s)
    canned_reply_for(Remark.new(s)) || DEFAULT_REPLY
  end
end

class Remark
  attr_reader :remark

  def initialize(s)
    @remark = s.to_s
  end

  def silence?
    remark.strip.empty?
  end

  def spoken?
    !silence?
  end

  def loud?
    spoken? && (remark == remark.upcase)
  end

  def question?
    remark.end_with?('?')
  end
end
