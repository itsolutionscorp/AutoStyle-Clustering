class Bob
  def hey(query_string)
    case Query.new(query_string)
    when :silence?.to_proc  then 'Fine. Be that way!'
    when :shout?.to_proc    then 'Woah, chill out!'
    when :question?.to_proc then 'Sure.'
    else                         'Whatever.'
    end
  end

  private

  class Query
    def initialize(query)
      @query = query
    end

    def silence?
      @query =~ /\A\s*\z/m
    end

    def shout?
      @query =~ /[a-z]/i && @query == @query.upcase
    end

    def question?
      @query =~ /\?\z/m
    end
  end
end
