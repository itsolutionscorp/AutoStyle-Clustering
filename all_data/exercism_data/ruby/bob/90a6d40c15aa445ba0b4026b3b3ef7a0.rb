class Bob
  def hey(sentence)
    case
      when question?(sentence)
        'Sure.'

      when shout?(sentence)
        'Woah, chill out!'

      when empty?(sentence)
        'Fine. Be that way!'
        
      else
        'Whatever.'
    end
  end

  def question?(sentence)
    sentence.end_with?("?") && sentence.scan(/[A-Z]{3,}/).empty?
  end

  def shout?(sentence)
    !sentence.scan(/[A-Z]{2,}/).empty? && sentence.scan(/[a-z]{2,}/).empty?
  end

  def empty?(sentence)
    sentence.empty? || sentence.scan(/\S/).empty?
  end
end
