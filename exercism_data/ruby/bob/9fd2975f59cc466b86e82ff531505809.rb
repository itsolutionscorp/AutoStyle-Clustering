class Bob
  def hey(call)
    TeenagerResponse.new(call).content
  end
end

class Call
  attr_reader :content

  def initialize(content)
    @content = content.rstrip
  end

  def shout?
    content.match(/[A-Z]/) && content == content.upcase
  end

  def silence?
    content.empty?
  end

  def question?
    content.end_with?("?") && !shout?
  end

  def text?
    content.match(/[:alpha:]/)
  end
end

class TeenagerResponse
  attr_reader :call

  def initialize(call)
    @call = Call.new(call)
  end

  def content
    if call.silence?
      "Fine. Be that way!"
    elsif call.question?
      "Sure."
    elsif call.shout?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
