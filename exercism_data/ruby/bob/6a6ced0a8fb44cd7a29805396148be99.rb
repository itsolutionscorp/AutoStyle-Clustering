class Bob
  def hey(greeting)
    you = You.new greeting

    if you.are_silent?
      'Fine. Be that way!'
    elsif you.yell_at_me?
      'Woah, chill out!'
    elsif you.ask_me_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  You = Struct.new(:greeting) do
    def are_silent?
      greeting.to_s =~ /^\s*$/
    end

    def yell_at_me?
      greeting =~ /^[^a-z]+$/
    end

    def ask_me_a_question?
      greeting =~ /\?$/
    end
  end
end
