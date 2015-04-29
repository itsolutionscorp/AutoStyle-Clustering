require 'pry'
class Bob

  def hey(convo)
    get_response(convo)
  end

  def get_response(convo)
    lower_case = convo.upcase != convo
    up_case = convo.upcase == convo
    if !convo.include?("?") and lower_case
      'Whatever.'
    elsif convo.empty?
      'Fine, be that way.'
    elsif !convo.include?("?") and up_case
      'Woah, chill out!'
    elsif convo.include?("?") and lower_case
      'Sure.'
    end
  end
end
