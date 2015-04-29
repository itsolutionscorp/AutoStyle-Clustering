class Bob
  #Strong dislike of the test_forceful_questions testcase, the question case was first in the readme, it should have priority over the caps case
  def hey(input)
    case
    when input.strip.empty?
      response = 'Fine. Be that way!'
    when input.upcase == input
      response = 'Woah, chill out!'
    when input.match(/\?$/)
      response = 'Sure.'
    else
      response = 'Whatever.'
    end
    
    return response
  end
end
