class Bob
  def hey(input)
    if input.strip.empty? then
      return 'Fine. Be that way!'
    elsif input.upcase == input and /[A-Z]+/ =~ input then
      return 'Woah, chill out!'
    elsif input.end_with?('?') then
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
