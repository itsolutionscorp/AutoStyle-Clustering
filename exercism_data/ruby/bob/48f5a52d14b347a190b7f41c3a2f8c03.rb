# Bob is a lackadaisical teenager. Written for the bob exercism.io assignment.
class Bob

  QUESTION_RESPONSE = 'Sure.'
  YELL_RESPONSE     = 'Woah, chill out!'
  SILENT_RESPONSE   = 'Fine. Be that way!'
  DEFAULT_RESPONSE  = 'Whatever.'

  # Returns a simple response for the providede expression.
  def hey(expression)

    case expression

    when /\A[^a-z]*[A-Z][^a-z]*\Z/
      YELL_RESPONSE

    when /\?\Z/
      QUESTION_RESPONSE

    when /\A\s*\Z/
      SILENT_RESPONSE

    else
      DEFAULT_RESPONSE

    end
  end
end
