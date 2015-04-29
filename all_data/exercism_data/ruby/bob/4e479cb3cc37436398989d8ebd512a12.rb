module InputAnalyzer
  def silence?(user_input)
    user_input.nil? || user_input.strip.empty?
  end
  
  def yelling?(user_input)
    user_input == user_input.upcase
  end
  
  def question?(user_input)
    user_input.end_with?("?")
  end
end

class Bob
  include InputAnalyzer
  
  def hey(user_input)
    case
    when silence?(user_input)
      'Fine. Be that way!'
    when yelling?(user_input)
      'Woah, chill out!'
    when question?(user_input)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
