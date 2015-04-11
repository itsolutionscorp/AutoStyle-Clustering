class Bob

  def hey(question)
    if question == question.upcase && !question.gsub(/[^a-zA-Z]/,'').empty?
      'Woah, chill out!'
    elsif question[-1] == '?'
      'Sure.'
    elsif question.gsub(' ','').empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
