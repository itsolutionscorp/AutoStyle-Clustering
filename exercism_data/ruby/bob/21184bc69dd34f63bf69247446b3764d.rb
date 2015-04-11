class Bob
  def hey(arg)
    case arg

    # Ask a question
    when /[a-z|\d]+.*\?\z/
      'Sure.'

    # Yelling
    when /\A[^a-z]+[A-Z]+.*\z/
      'Woah, chill out!'

    # Not saying anything
    when /\A\s*\z/
      'Fine. Be that way!'

    else
      'Whatever.'
    end
  end
end
