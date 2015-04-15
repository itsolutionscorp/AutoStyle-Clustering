class Bob
  
  def hey(str)
    if need_to_chill?(str)
      'Whoa, chill out!'
    elsif asking_question?(str)
      'Sure.'
    elsif silence?(str)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
    def need_to_chill?(str)
      str = remove_non_letters(str)
      str.upcase.eql?(str) && !str.empty?
    end

    def asking_question?(str)
      str[-1].eql? '?'
    end

    def silence?(str)
      str.strip.empty?
    end

    def remove_non_letters(str)
      str.scan(/[a-zA-Z]+/).join
    end

end
