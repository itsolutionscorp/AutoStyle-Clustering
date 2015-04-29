class Bob
  CannedReplies = {
    loud:     "Woah, chill out!",
    empty:    "Fine. Be that way!",
    question: "Sure."
  }
  DefaultReply = "Whatever."

  def canned_reply_for(remark)
    (canned = CannedReplies.find { |k,v| v if remark.send("#{k}?") }) && canned.last
  end

  def hey(s)
    canned_reply_for(Remark.new(s)) || DefaultReply
  end
end

class Remark
  attr_reader :remark

  def initialize(s)
    @remark = s.to_s
  end

  def empty?
    remark.empty?
  end

  def loud?
    !empty? && remark == remark.upcase
  end

  def question?
    remark =~ /\?$/
  end
end
