Statement = Struct.new(:content) do
  def initialize content
    self.content = content.to_s
  end

  def silence?
    content.empty?
  end

  def question?
    content.end_with? '?'
  end

  def yelling?
    content == content.upcase
  end
end

class Bob
  def hey input
    respond_to Statement.new(input)
  end

  def respond_to statement
    if statement.silence?
      "Fine. Be that way!"
    elsif statement.yelling?
      "Woah, chill out!"
    elsif statement.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end
