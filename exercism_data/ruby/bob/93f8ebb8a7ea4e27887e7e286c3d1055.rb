class Bob

  def hey(statement)
    get_response(statement)
  end

  private
    def get_response(statement)
      if silence?(statement)
        "Fine. Be that way."
      elsif question?(statement)
        "Sure."
      elsif yell?(statement)
        "Woah, chill out!"
      else
        "Whatever."
      end
    end

    def silence?(statement)
      statement.nil? || statement.empty?
    end

    def question?(statement)
      statement.end_with?("?")
    end

    def yell?(statement)
      statement == statement.upcase
    end
end
