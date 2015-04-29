class Bob

  RESPONSES = {
    :question      => 'Sure.',
    :yell          => 'Woah, chill out!',
    :silence       => 'Fine. Be that way!',
    :anything_else => 'Whatever.'
  }

  def hey phrase
    RESPONSES[ type_of_answer( phrase ) ]
  end

private

  def type_of_answer phrase
    case phrase
      when /\A(?=.*[A-Z].*)[^a-z]+\z/
       :yell
      when /\?\z/
        :question 
      when /\A\s*\z/
        :silence
      else
        :anything_else
    end
  end

end
