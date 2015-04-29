class Bob

  def point_or_new_string?(speech)
    speech.end_with? '.' or speech.include? "\n"
  end

  def only_numbers_and_quotes?(speech)
    speech.match(/[1-3]$/) or speech.include? "'"
  end

  def all_capital_letters?(speech)
    speech.upcase.eql? speech and speech.scan(/[0-9]/).empty?
  end

  def hey(speech)
    if speech.strip.empty?
      return 'Fine. Be that way!'
    elsif point_or_new_string?(speech) or only_numbers_and_quotes?(speech)
      return 'Whatever.'
    elsif speech.end_with? '!' or all_capital_letters?(speech)
      return 'Woah, chill out!'
    elsif speech.end_with? '?'
      return 'Sure.'
    end
  end
end
