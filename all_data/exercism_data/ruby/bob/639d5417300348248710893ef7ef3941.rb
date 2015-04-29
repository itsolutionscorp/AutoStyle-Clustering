class Bob
  CannedReplies = {
    loud:     "Woah, chill out!",
    empty:    "Fine. Be that way!",
    question: "Sure."
  }

  def reply_to(remark)
    reply = CannedReplies.find { |k,v| remark.send("#{k}?") }
    reply ? reply.last : "Whatever."
  end

  def hey(s)
    reply_to(Remark.new(s))
  end
end

class Remark
  attr_reader :remark

  def initialize(s)
    @remark = s
  end

  def empty?
    !remark || remark.empty?
  end

  def loud?
    !empty? && remark == remark.upcase
  end

  def question?
    !empty? && remark =~ /\?$/
  end
end
