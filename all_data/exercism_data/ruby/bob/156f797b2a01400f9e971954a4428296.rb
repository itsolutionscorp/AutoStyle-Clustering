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
      has_any_of? ('a'..'z').to_a
    end

    def has_uppercase?
      has_any_of? ('A'..'Z').to_a
    end

    def has_any_of?(letters)
      letters.to_a.any? { |letter| query.include?(letter) }
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
