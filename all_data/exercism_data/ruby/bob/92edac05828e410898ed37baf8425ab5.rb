class Bob
  def hey sentence
    if question? sentence
      'Sure.'
    elsif silent? sentence
      'Fine. Be that way!'
    elsif yelling? sentence
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def question? sentence
    sentence.end_with? '?' unless sentence[/^[A-Z\s]+\?/]
  end

  def silent? sentence
    sentence.strip.empty?
  end

  def yelling? sentence
    sentence.upcase == sentence unless sentence[/[[0-9],*\s*]+$/]
  end
end
