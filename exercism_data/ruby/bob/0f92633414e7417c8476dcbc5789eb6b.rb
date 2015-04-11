class Bob
  class Query < Struct.new(:query)
    def not_saying_anything?
      query.strip.empty?
    end

    def screaming?
      ! has_lowercase_letters? and has_uppercase_letters?
    end

    def asking_a_question?
      query.end_with?('?')
    end

    private
    def has_lowercase_letters?
      /[a-z]/ =~ query
    end

    def has_uppercase_letters?
      /[A-Z]/ =~ query
    end
  end

  def hey(query_string)
    query = Query.new(query_string)
    
    if query.not_saying_anything?
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
