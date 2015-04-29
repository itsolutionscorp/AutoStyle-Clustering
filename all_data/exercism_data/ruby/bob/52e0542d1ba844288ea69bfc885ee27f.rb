class Bob
  class Query < Struct.new(:query)
    def empty?
      query.strip.empty?
    end

    def screaming?
      has_uppercase? and ! has_lowercase?
    end

    def asking_a_question?
      query.end_with?('?')
    end

    private
    def has_lowercase?
      /[a-z]/ =~ query
    end

    def has_uppercase?
      /[A-Z]/ =~ query
    end
  end

  def hey(query_string)
    query = Query.new(query_string)
    
    if query.empty?
      'Fine. Be that way!'
    elsif query.screaming? 
      'Woah, chill out!'
    elsif query.asking_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
