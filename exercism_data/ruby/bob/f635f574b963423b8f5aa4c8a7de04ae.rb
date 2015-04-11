class Bob
  def hey (my_string)
    if !my_string.match(/[a-z]/) && my_string.match(/[A-Z]/)
      # He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
      'Woah, chill out!'
    elsif my_string[-1] == '?'
      # Bob answers 'Sure.' if you ask him a question.
      'Sure.'
    elsif my_string.nil? || my_string.empty? || my_string.match(/\A[\s]+\z/)
      # He says 'Fine. Be that way!' if you address him without actually saying anything.
      'Fine. Be that way!'
    else
      # He answers 'Whatever.' to anything else.
      'Whatever.'
    end
  end
end
