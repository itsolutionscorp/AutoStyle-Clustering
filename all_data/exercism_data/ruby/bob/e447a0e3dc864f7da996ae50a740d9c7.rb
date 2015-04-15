class Bob

  def hey(statement)
    return 'Fine. Be that way!' if is_silent(statement)
    return 'Woah, chill out!'   if is_shouting(statement)
    return 'Sure.'              if is_question(statement)
    return 'Whatever.'
  end

  private

    def is_silent(statement)
      just_whitespace = /\A\s*\z/
      just_whitespace.match(statement)
    end

    def is_shouting(statement)
      has_letters = /[a-zA-Z]/
      has_letters.match(statement) and statement.upcase == statement
    end

    def is_question(statement)
      statement.end_with?('?')
    end
end
