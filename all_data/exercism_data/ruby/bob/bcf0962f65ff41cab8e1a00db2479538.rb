class Bob
  def hey(statement)
    if shouting?(statement)
      'Whoa, chill out!'
    elsif question?(statement)
      'Sure.'
    elsif silence?(statement)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

    def shouting? statement
      statement =~ /[a-zA-Z]/ && statement == statement.upcase
    end

    def question? statement
      statement.end_with?("?")
    end

    def silence? statement
      statement.strip.empty?
    end
end
