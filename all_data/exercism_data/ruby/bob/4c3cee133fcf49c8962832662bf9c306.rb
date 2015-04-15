class Bob
  def new
  end

  def hey(command)
    statement = Statement.new(command)
    response  = Response.new

    if statement.is_valid_statement?
      case
      when statement.is_yelling? && statement.contains_words?
        response.to_yelling
      when statement.is_question?
        response.to_question
      else
        response.default
      end
    elsif statement.blank?
      response.to_saying_nothing
    else
      response.default
    end
  end
end

class Statement < String
  def new(value)
  end

  def is_valid_statement?
    /\w(\!|\.|\?)/ =~ self or self.contains_words?
  end

  def is_question?
    self[-1] === "?"
  end

  def is_yelling?
    self === self.upcase 
  end

  def contains_words?
    /([a-zA-Z]+\s*[a-zA-Z]*)+\w/ =~ self
  end

  # Implementing my implementation of Rails' .blank? method
  def blank?
    self.strip.length === 0
  end
end

class Response < String
  def new
  end

  def to_yelling
    "Woah, chill out!" 
  end

  def to_question
    "Sure."
  end

  def to_saying_nothing
    "Fine. Be that way!"
  end

  def default
    "Whatever."
  end
end
