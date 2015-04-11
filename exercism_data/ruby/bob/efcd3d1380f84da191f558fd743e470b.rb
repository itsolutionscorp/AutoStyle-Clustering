class Bob
  @@responses = { :silent => 'Fine. Be that way!',
                  :shout => 'Woah, chill out!',
                  :question => 'Sure.' }

  def hey(statement)
    statement_type = Statement.new(statement).analyze

    if @@responses.has_key? statement_type
      return @@responses[statement_type]
    else
      return 'Whatever.'
    end

  end
end

class Statement
  def initialize(statement)
    @statement = statement
  end

  def analyze
    if is_silent? @statement
      return :silent

    elsif is_shouting? @statement
      return :shout

    elsif is_question? @statement
      return :question

    else
      return :general
    end
  end

  def is_silent? statement
    return statement.strip == ''
  end

  def is_shouting? statement
    return statement.upcase == statement
  end

  def is_question? statement
    return statement.strip.end_with? '?'
  end
end
