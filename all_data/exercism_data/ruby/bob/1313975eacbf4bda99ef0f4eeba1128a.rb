class Bob
  def hey(string)
    stripped_string = string.strip unless string.nil?
    case
    when string.nil? || stripped_string.empty? then
      'Fine. Be that way!'
    when string == string.upcase then
      'Woah, chill out!'
    when stripped_string.end_with?('?') then
      'Sure.'
    else
      'Whatever.'
    end
  end
end

bob = Bob.new
