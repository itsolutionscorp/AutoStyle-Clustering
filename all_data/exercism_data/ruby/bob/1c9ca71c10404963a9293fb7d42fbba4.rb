class Bob
    def hey(arg)
      if arg == arg.upcase && arg.upcase != arg.downcase
        'Woah, chill out!'
      elsif arg.end_with?('?')
        'Sure.'
      elsif arg.gsub(' ', '').gsub("\t", '') == ''
        'Fine. Be that way!'
      else
        'Whatever.'
      end
    end




end
