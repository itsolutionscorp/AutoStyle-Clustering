class Bob
  def initialize
  end

  def hey(phrase)
    if phrase.strip.empty?
      'Fine. Be that way!'
    elsif phrase.match(/[A-Z]/) && phrase == phrase.upcase
      'Whoa, chill out!'
    elsif phrase.strip[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
