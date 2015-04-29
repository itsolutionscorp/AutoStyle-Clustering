class Bob
  def hey text
    dialogist = Dialogist.new text
    reply_to dialogist
  end

  def reply_to person
    case
    when person.is_shouting? then "Woah, chill out!"
    when person.is_asking? then "Sure."
    when person.says_nothing? then "Fine. Be that way!" 
    else
      "Whatever."
    end
  end
end

class Dialogist
  def initialize sentence
    @sentence = sentence.to_s
  end

  def is_shouting?
    unless says_nothing?
      @sentence == @sentence.upcase
    end
  end

  def is_asking?
    @sentence.end_with? "?"
  end

  def says_nothing?
    @sentence.strip.empty?
  end
end
