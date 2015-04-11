class Bob
  def hey(answer)
    if (answer.count " ") > 2 && (answer.count "^ ") < 1
      return 'Fine. Be that way!'
    elsif answer.empty?
      return 'Fine. Be that way!'
    elsif (answer.count "0-9") > 0
      if answer[answer.length-1, answer.length-1] == '!'
        return 'Woah, chill out!'
      elsif answer[answer.length-1, answer.length-1] == '?'
        return 'Sure.'
      else
        return 'Whatever.'
      end
    elsif answer === answer.upcase && answer[answer.length-1, answer.length-1] == '?'
      return 'Woah, chill out!'
    elsif answer === answer.upcase
      return 'Woah, chill out!'
    elsif answer[answer.length-1, answer.length-1] == '?'
      return 'Sure.'
    elsif answer != answer.nil?
      return 'Whatever.'
    end
  end
end
