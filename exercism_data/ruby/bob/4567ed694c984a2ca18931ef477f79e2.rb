class Bob
  RESPONSES = {
    silence: {
      check:    -> (input) { '' == input },
      response: 'Fine. Be that way!'
    },
    shouting: {
      check:    -> (input) { input == input.upcase },
      response: 'Woah, chill out!'
    },
    question: {
      check:    -> (input) { input.end_with?('?') },
      response: 'Sure.'
    }
  }

  def hey(raw_text)
    text = sanitize(raw_text)
    RESPONSES.each do |k, v|
      return v[:response] if v[:check].call(text)
    end

    'Whatever.'
  end

  private

  def sanitize(input)
    input ? input.strip : ''
  end
end
