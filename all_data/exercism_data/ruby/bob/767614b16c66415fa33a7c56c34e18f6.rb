class Bob
  def hey(message)

    s = Statement.new(message)
    case 
      when s.silent? then "Fine. Be that way!"
      when s.forceful? then "Woah, chill out!"
      when s.query? then "Sure."
    else
      "Whatever."
    end
  end
end

class Statement

  def initialize(message)
    @message = message
  end

  def forceful? 
    @message =~ /[A-Z]/ && @message.upcase == @message
  end

  def query?
     @message.end_with?("?") 
  end

  def silent? 
    @message !~ /\S/ || nil
  end
end
