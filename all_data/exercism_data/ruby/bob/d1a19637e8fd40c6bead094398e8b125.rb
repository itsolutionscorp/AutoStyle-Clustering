class Bob
  def hey(chain)
    if chain.nil? || chain.empty?
      'Fine. Be that way!'
    elsif chain == chain.upcase
      'Woah, chill out!'
    elsif chain.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
