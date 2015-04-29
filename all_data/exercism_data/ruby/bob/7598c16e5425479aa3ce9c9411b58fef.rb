class Bob
  IS_A_NON_FORECFULL_QUESTION = Proc.new { |s| s.end_with?("?") && s.upcase != s }
  IS_SHOUTING                 = Proc.new { |s| s.upcase == s }
  IS_NUMERIC_QUESTION         = Proc.new { |s| !!s.match(/\d+/) }
  SAY_NOTHING                 = Proc.new { |s| !!s.split.join("").match(/^\s*$/) }

  def hey(message)
    case message
    when IS_A_NON_FORECFULL_QUESTION      then 'Sure.'
    when SAY_NOTHING                      then 'Fine. Be that way!'
    when IS_SHOUTING, IS_NUMERIC_QUESTION then 'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
