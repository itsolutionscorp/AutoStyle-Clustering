class Bob
  def hey(argument)
   if (argument == argument.upcase) && (argument =~ /[A-Z]/)
      'Woah, chill out!'
    elsif argument.end_with?('?')
      'Sure.'
    elsif argument.upcase != argument.downcase
      'Whatever.'
    elsif argument.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
