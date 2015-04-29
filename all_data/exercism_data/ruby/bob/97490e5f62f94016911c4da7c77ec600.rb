class Bob
  def hey(words)
    request = Request.new(words)

    if request.nothing?
      "Fine. Be that way."
    elsif request.shouting?
      "Woah, chill out!"
    elsif request.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Request
  def initialize(words)
    @words = words
  end

  def nothing?
    @words.nil? || @words.empty?
  end

  def shouting?
    @words == @words.upcase
  end

  def question?
    @words.end_with?("?")
  end
end
