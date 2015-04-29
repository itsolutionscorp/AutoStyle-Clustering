class Bob
  def hey(words_i_said)
    def is_asking?(words_i_said)
      words_i_said.end_with?("?")
    end
    def is_yelling?(words_i_said)
      words_i_said.downcase !=words_i_said.upcase && words_i_said == words_i_said.upcase
    end
    def is_silent?(words_i_said)
      words_i_said.squeeze == "" ||words_i_said.squeeze == " "
    end


    if is_silent?(words_i_said)
      'Fine. Be that way!'
    elsif is_yelling?(words_i_said)
      'Woah, chill out!'
    elsif is_asking?(words_i_said)
      'Sure.'
    else
      'Whatever.'
    end

  end
end
