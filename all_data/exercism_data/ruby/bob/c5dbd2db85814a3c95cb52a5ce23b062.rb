class Bob
  def hey sentence
    if sentence.end_with?"?" then
      'Sure.'
    elsif sentence.end_with?"!" then
      'Woah, chill out!'
    elsif sentence.empty? then
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
