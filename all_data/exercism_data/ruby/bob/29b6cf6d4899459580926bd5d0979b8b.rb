class Bob
  def hey text
    dialogist = Dialogist.new text
    reply = case
            when dialogist.is_shouting? then "Woah, chill out!"
            when dialogist.is_asking? then "Sure."
            when dialogist.says_nothing then "Fine. Be that way!" 
            else
              "Whatever."
            end
  end
end

class Dialogist
  def initialize(sentence)
    @sentence = sentence.strip
  end

  def is_shouting?
    unless says_nothing
      @sentence == @sentence.upcase
    end
  end

  def is_asking?
    @sentence.end_with?("?")
  end

  def says_nothing
    @sentence.empty?
  end
end
