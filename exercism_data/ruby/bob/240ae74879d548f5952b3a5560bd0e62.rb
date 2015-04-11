class Bob

  def hey(said)
    if shouting(said) || asking_forceful_question(said)
      'Woah, chill out!'
    elsif asking_a_question(said) || prattling_on(said)
      'Sure.'
    elsif silence(said)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shouting(said)
    said == said.upcase unless talking_numbers(said)
  end

  def asking_a_question(said)
    said =~ /\?$/ unless multiple_line_question(said)
  end

  def asking_forceful_question(said)
    asking_a_question(said) && shouting(said)
  end

  def prattling_on(said)
    chars_array = said.split('')
    chars_array.include?(/(!|\?|.)+/)
  end

  def talking_numbers(said)
    words_array = said.split(/[\s,]+/)
    words_array.count{ |n| n =~ /\d/ } == words_array.size
  end

  def silence(said)
    said =~ /^\s*$/ unless multiple_line_question(said)
  end

  def multiple_line_question(said)
    said =~ /\n+/
  end

end
