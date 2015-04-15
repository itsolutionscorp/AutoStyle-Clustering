class Bob

  def hey(statement)
    case 
    when statement.upcase == statement && statement.gsub(' ', '') != ''
      'Woah, chill out!'
    when statement.end_with?("?")
      'Sure.'
    when statement.gsub(' ', '') == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
#end of Bob class
end
