class Bob
  def hey(message)
    message ||= ''

    case message
    when SILENT
      'Fine. Be that way!'
    when SHOUTING
      'Woah, chill out!'
    when QUESTION
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  LETTER = ->(character) { character.match(/\A[A-Za-z]\Z/) }
  UPPERCASE_LETTER = ->(character) { character.match(/\A[A-Z]\Z/) }

  SILENT = ->(message) { message.match(/^(\s*)$/) }
  SHOUTING = ->(message) { message.each_char.select(&LETTER).all?(&UPPERCASE_LETTER) }
  QUESTION = ->(message) { message.end_with?('?') }
end
