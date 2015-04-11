class Bob
  def hey argument
    return 'Fine. Be that way!' if is_empty? argument
    return 'Woah, chill out!' if is_scream? argument
    return 'Sure.' if is_question? argument
    return 'Whatever.'
  end

  private

  def is_empty? argument
    argument.strip.empty?
  end
  
  def is_scream? argument
    argument =~ /[A-Z]/ && argument.upcase == argument
  end

  def is_question? argument
    argument.end_with?("?")
  end
end
