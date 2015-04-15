class Bob
  def hey(statement)
    provocation = Query.new(statement)
    if provocation.is_a_blowoff?
      'Fine. Be that way!'
    elsif provocation.is_shouting?
      'Woah, chill out!'
    elsif provocation.is_asking?
      'Sure.'
    else
      'Whatever.'
    end
  end
end


class Query
  def initialize(statement)
    @statement = statement
  end

  def is_a_blowoff?
    @statement.strip.empty?
  end

  def is_shouting?
    @statement.upcase == @statement && (@statement =~ /[A-Z]/)
  end

  def is_asking?
    @statement.end_with?('?')
  end
end
