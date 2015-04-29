class Bob
  

  def hey(input)
      if input.strip.length == 0
        'Fine. Be that way!'
      elsif input == input.upcase && input.downcase != input
         'Woah, chill out!'
      elsif input[-1] == '?'
          'Sure.'
      else      
         'Whatever.'
      end

  end


end
