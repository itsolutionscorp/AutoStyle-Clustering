class Bob

  def initialize
  end

  def hey(words)
    if words == ''
      'Fine. Be that way.'
    elsif shouting?(words)
      'Woah, chill out!'
    elsif question?(words)
      'Sure.'
    elsif statement?(words)
      'Whatever.'
    end
  end

private
  def shouting?(words)
    words == words.upcase
  end

  def statement?(words)
    words[1..-1] == words[1..-1].downcase
  end

  def question?(words)
    words.end_with?("?")
  end
end
