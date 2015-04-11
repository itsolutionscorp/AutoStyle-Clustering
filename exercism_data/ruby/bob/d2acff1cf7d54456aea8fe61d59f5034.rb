class Bob

  SOME_UPPER = /[A-Z]+/ 
  NO_LOWER   = /\A[^a-z]+\Z/
  QUESTION   = /\?\Z/
  SILENCE    = /\A\s*\Z/

  def hey remark
    match = ->(regex) { !!(remark.match regex) }

    is_yelling  = ->() { match[SOME_UPPER] && match[NO_LOWER] }
    is_question = ->() { match[QUESTION] }
    is_silence  = ->() { match[SILENCE] }

    return 'Fine. Be that way!' if is_silence.call
    return 'Whoa, chill out!'   if is_yelling.call
    return 'Sure.'              if is_question.call
    return 'Whatever.'
  end
end
