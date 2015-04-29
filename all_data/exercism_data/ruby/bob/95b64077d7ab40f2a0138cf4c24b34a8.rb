class Bob
  RESPONSES = [{matcher: :empty?, answer: 'Fine. Be that way!'},
               {matcher: :yelling?, answer: 'Woah, chill out!'},
               {matcher: :question?, answer: 'Sure.'}]

  def hey(query_string)
    query = Query.new(query_string)
    RESPONSES.each do |response|
      return response[:answer] if query.send(response[:matcher])
    end
    'Whatever.'
  end

  Query = Struct.new(:query_string) do
    def yelling?
      query_string.upcase == query_string
    end

    def question?
      query_string.chars.last == '?'
    end

    def empty?
      query_string.nil? or query_string.empty?
    end
  end
end
