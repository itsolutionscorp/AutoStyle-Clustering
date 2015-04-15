class Bob
  def hey(input)
    case input.strip
    when yelling? ; 'Woah, chill out!'
    when /\?\z/   ; 'Sure.'
    when ''       ; 'Fine. Be that way!'
    else            'Whatever.'
    end
  end

  private

  def yelling?
    lambda { |input| input =~ /[A-Z]+/ && input !~ /[a-z]+/ }
  end
end
