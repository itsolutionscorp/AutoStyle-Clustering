class Bob
  class Query < Struct.new(:query)
    def empty?
      query.strip.empty?
    end

    def screaming?
      alphabet = ('a'..'z').to_a + ('A'..'Z').to_a
      alphabet.any? {|letter| query.include?(letter) } and
        query.upcase == query
    end

    def asking_a_question?
      query.end_with?('?')
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
