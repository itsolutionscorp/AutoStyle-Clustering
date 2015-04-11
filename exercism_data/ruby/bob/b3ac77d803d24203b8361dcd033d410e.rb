class Bob
  def hey(input)
    if input.match(/\p{Lower}/)
      with_lowercase_letter(input)
    elsif input.strip.empty?
      'Fine. Be that way!'
    else
      'Woah, chill out!'
    end
  end

  def with_lowercase_letter(input)
    if input.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
