class Bob
  @@responses = { :silent => 'Fine. Be that way!',
                  :shout => 'Woah, chill out!',
                  :question => 'Sure.' }

  def hey(statement)
    statement_type = Statement.new(statement).analyze

    if @@responses.has_key? statement_type
      @@responses[statement_type]
    else
      'Whatever.'
    end

  end
end

class Statement
  def initialize(statement)
    @statement = statement || ''
  end

  def analyze
    if silent?
      :silent
    elsif shouting?
      :shout
    elsif question?
      :question
    else
      :general
    end
  end

  def silent?
    @statement.strip == ''
  end

  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.strip.end_with? '?'
  end
end
