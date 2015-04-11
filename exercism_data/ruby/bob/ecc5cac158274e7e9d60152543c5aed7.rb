class Bob
  def hey(string)

    string.strip! unless string.nil?
    if string.nil? or string.empty?
      'Fine. Be that way!'
    elsif string == string.upcase
      'Woah, chill out!'
    elsif string.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end

bob = Bob.new
