class Bob
  EMPTY_SPACE_REGEX=/\A\s*\Z/
  UPCASE_REGEX=/\A[^a-z]*[A-Z]+[^a-z]*\Z/
  def hey(msg)
    if UPCASE_REGEX.match(msg)
      'Woah, chill out!'
    elsif msg.end_with? '?'
      'Sure.'
    elsif EMPTY_SPACE_REGEX.match(msg)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
