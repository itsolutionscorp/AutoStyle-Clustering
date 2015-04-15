class Statement
  def initialize(text)
    @text = text
  end

  def shouting?
    has_capital_letter? && @text == @text.upcase 
  end

  def question?
    @text.end_with? "?"
  end

  def silent?
    @text && @text.strip.length == 0
  end

  private
  def has_capital_letter?
    @text =~ /[[:upper:]]+/
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
