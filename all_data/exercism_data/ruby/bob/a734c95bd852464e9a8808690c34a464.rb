class Bob

  def hey(statement)
    return 'Fine. Be that way!' if silent?(statement)
    return 'Woah, chill out!'   if shouting?(statement)
    return 'Sure.'              if question?(statement)
    return 'Whatever.'
  end

  private

    def silent?(statement)
      statement.strip.empty?
    end

    def shouting?(statement)
      has_letters = /[a-zA-Z]/
      has_letters.match(statement) && statement.upcase == statement
    end

    def question?(statement)
      statement.end_with?('?')
    end
end
