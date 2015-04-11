class Bob
  def hey(words)
   words ||= ''

   if silent?(words)
     'Fine. Be that way!'
   elsif yelling?(words)
     'Woah, chill out!'
   elsif question?(words)
     'Sure.'
   else
     'Whatever.'
   end
  end

  private

  def silent?(words)
    words.strip.empty?
  end

  def yelling?(words)
    words == words.upcase
  end

  def question?(words)
    words.end_with?('?')
  end
end
