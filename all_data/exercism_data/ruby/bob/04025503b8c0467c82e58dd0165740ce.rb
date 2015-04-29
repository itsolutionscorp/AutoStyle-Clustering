class Bob
  RESPONSES = {
    question: 'Sure.',
    exclamation: 'Woah, chill out!',
    empty: 'Fine. Be that way!',
    whatever: 'Whatever.',
  }

  def hey(statement)
    RESPONSES[parse(statement)]
  end

  private
    def parse(statement)
      stripped_statement = strip_statement(statement)

      if is_empty?(stripped_statement)
        :empty
      elsif is_exclamation?(stripped_statement)
        :exclamation
      elsif is_question?(stripped_statement)
        :question
      else
        :whatever
      end
    end

    def strip_statement(statement)
      statement.gsub(/\s+/, '')
    end

    def is_empty?(statement)
      statement.size == 0
    end

    def is_exclamation?(statement)
      statement.match(/[A-Z]/) && statement.upcase == statement
    end

    def is_question?(statement)
      statement[-1] == '?'
    end
end
