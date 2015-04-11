class Bob
  def hey(query_string)
    query = Query.new(query_string)
    if query.empty?
      'Fine. Be that way!'
    elsif query.yelling?
      'Woah, chill out!'
    elsif query.question?
      'Sure.'
    else
      'Whatever.'
    end
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
