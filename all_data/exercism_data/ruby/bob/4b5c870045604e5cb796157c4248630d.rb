class Bob

  def hey(response)

    response ||= ""
 
    response = Response.new(response)
 
    case
    when response.shouting?
      "Woah, chill out!"
    when response.question?
      "Sure."
    when response.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
 
  private

  class Response < String

    def shouting?
      return false if empty? 
      self == upcase 
    end

    def is_i?
       !!(self =~ /^[-+]?[0-9]+$/)
    end

    def question?
      end_with? "?"
    end

  end

end
