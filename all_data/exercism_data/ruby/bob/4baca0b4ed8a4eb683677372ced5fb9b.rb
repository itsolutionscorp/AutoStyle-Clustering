class Bob
  def hey(string)
    case
    when (string.nil? or string.strip.empty?) then
      'Fine. Be that way!'
    when string == string.upcase then
      'Woah, chill out!'
    when string.strip.end_with?('?') then
      'Sure.'
    else
      'Whatever.'
    end
  end
end

bob = Bob.new
