class Statement
  def initialize(text)
    @text = text
  end

  def shouting?
    @text =~ /[[:upper:]]+/ && @text == @text.upcase 
  end

  def question?
    @text.end_with? "?"
  end

  def silent?
    @text && @text.strip.empty?
  end
end

class Bob
  def hey(message)
    statement = Statement.new(message)
    if statement.shouting?
      "Woah, chill out!"
    elsif statement.question?
      "Sure."
    elsif statement.silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
