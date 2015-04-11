class Bob
  def hey(phrase)
    if phrase.match(/[^a-z][^\w][A-Z]{2,}/)
      'Woah, chill out!'
    elsif phrase.match(/\A^\s+\z/) || phrase.empty?
      'Fine. Be that way!'
    elsif phrase.match(/[?]\z/)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
