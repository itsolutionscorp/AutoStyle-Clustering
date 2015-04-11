class Bob
  def hey(input)

    if input == input.upcase && input.match(/[A-Z]/)
      answer = 'Whoa, chill out!'
    elsif input[input.length-1] ==  '?'
      answer = 'Sure.'
    elsif !(input.nil? || input =~ /\S/)
      answer = 'Fine. Be that way!'
    else
      answer = 'Whatever.'
    end

    return answer
  end
end
