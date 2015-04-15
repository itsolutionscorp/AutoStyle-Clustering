class Bob

  def hey(speach)
    @statement = Statement.new(speach)

    if @statement.nothing?
      'Fine. Be that way!'
    elsif @statement.shouting?
      'Woah, chill out!'
    elsif @statement.question?
      "Sure."
    else
      "Whatever."
    end
    
  end

end

class Statement
  def initialize(text)
    @text = text.strip
  end

  def question?
    @text.end_with?('?')
  end

  def nothing?
    @text.empty?
  end

  def shouting?
    self.all_upcase? && self.contains_letters?
  end

  def all_upcase?
    @text == @text.upcase
  end

  def contains_letters?
    @text =~ /[A-Z]/
  end

end
