class Bob

  def hey(stating_argument)

    if stating_argument == stating_argument.upcase && stating_argument =~
      /[A-Z]/

      return 'Woah, chill out!'

    elsif stating_argument.end_with?('?')

      return 'Sure.'

    elsif stating_argument.strip.empty?

      return 'Fine. Be that way!'

    else

      return 'Whatever.'

    end

  end

end
