class Bob
  def hey(phrase)
    phrase.strip!

    return 'Whoa, chill out!' if phrase.upcase == phrase && phrase.downcase != phrase

    case phrase[-1]
    when '?' then 'Sure.'
    when nil then 'Fine. Be that way!'
    else          'Whatever.'
    end
  end
end
