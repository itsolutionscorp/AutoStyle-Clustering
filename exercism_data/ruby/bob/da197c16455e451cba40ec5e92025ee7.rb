class Bob
  def hey(something)
    word = something.gsub(/\s+/, '')
    if(word=='')
      return 'Fine. Be that way!'
    end
    num_big_letter = word.count 'A-Z'
    num_small_letter = word.count 'a-z'
    if(num_big_letter>0 and num_small_letter==0)
      return 'Woah, chill out!'
    end
    is_question = word[-1,1] == '?'
    if(is_question)
      return 'Sure.'
    end
    return 'Whatever.'
  end
end
