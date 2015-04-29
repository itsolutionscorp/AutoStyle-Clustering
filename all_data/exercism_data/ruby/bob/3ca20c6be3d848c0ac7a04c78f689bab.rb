class Bob
  def hey(input)
    if input.strip == ''
      'Fine. Be that way!'
    elsif input == input.upcase
      'Woah, chill out!'
    elsif is_question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_question?(input)
    input.chars.last == '?'
  end
end
