class Bob
  def hey(text)
    statement = Statement.new(text)

    if statement.shouting?
      'Woah, chill out!'
    elsif statement.question?
      'Sure.'
    elsif statement.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Statement < Struct.new(:text)
  def shouting?
    upcase? && includes_letters?
  end

  def question?
    text.end_with?('?')
  end

  def silence?
    text.strip.empty?
  end

  private

  def upcase?
    text.upcase == text
  end

  def includes_letters?
    text.downcase != text
  end
end
