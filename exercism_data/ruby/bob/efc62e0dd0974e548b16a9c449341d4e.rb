class Bob

  def hey (sentence)

    cs = sentence.gsub(/[\W0-9]/, '')
    if sentence.strip.empty?
      'Fine. Be that way!'
    elsif !cs.empty?  && !cs.upcase!
      'Woah, chill out!'
    elsif sentence.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
