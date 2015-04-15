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
      elsif tell?(statement)
        "Whatever."
      elsif yell?(statement)
        "Woah, chill out!"
      end
    end

    def silence?(statement)
      true if statement.nil? || statement.empty?
    end

    def question?(statement)
      true if statement.match(/.+\?\z/)
    end

    def tell?(statement)
      true if statement.match(/[A-Z][a-z]{2,}/)
    end

    def yell?(statement)
      true if statement.match(/[A-Z]{2,}/)
    end
end
