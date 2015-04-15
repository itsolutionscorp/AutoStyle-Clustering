class Bob
  class Query < Struct.new(:query)
    def empty?
      query.strip.empty?
    end

    def screaming?
       has_any_letters? and query.upcase == query
    end

    def asking_a_question?
      query.end_with?('?')
    end

    private
    def has_any_letters?
      alphabet = ('a'..'z').to_a + ('A'..'Z').to_a
      alphabet.any? {|letter| query.include?(letter) }
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
