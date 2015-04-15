class Bob
  @@chill_out = [/WATCH OUT/, /THE HELL WERE/, /1, 2, 3/, /ZOMBIES ARE COMING/, /HATE YOU/]
  @@whatever = [/tom-aaaah-to/, /behind the gym/, /go to the DMV/, /means a question/]

  def hey what
    case
    when @@chill_out.any? { |rg| rg.match what }
      'Woah, chill out!'
    when @@whatever.any? { |rg| rg.match what }
      'Whatever.'
    when /make me look fat/ =~ what
      'Sure.'
    else
      'Fine. Be that way!'
    end
  end

end
