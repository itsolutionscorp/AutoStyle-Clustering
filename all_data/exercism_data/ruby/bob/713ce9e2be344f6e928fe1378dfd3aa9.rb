class Bob

  def run
    puts "Yo. I'm Bob. Whaddaya want?"
    input = ""
    while input != 'bye'
      puts ''
      printf 'well?'
      input = gets.chomp
      hey(input)
    end
  end

  def shouting(input)
    input == input.upcase
  end

  def question(input)
    input[-1] == '?'
  end

  def silence(input)
    input.strip == ""
  end

  def hey(input)
    case
    when silence(input)
      'Fine. Be that way!'
    when shouting(input) 
      'Woah, chill out!'
    when question(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

end

#comment out the last two lines when running the test!

bob = Bob.new
bob.run
