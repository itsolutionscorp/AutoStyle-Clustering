class Bob
  def hey(sentence)


    if ( (sentence.nil?) or (sentence == '') )
      'Fine. Be that way!'
    else
      if ( (sentence.chars.last == '?') and (sentence != sentence.upcase) )
        'Sure.'
      else
        if sentence == sentence.upcase
          'Woah, chill out!'
        else
          'Whatever.'
        end
      end
    end

  end
end
