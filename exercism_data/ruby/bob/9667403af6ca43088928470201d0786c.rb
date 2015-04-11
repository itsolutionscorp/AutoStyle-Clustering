class Bob

  def hey(str)

    if str == str.upcase && str =~ /[A-Z]/
      'Whoa, chill out!'

    elsif str.end_with?("?")
      'Sure.'

    elsif str.strip == ""
      'Fine. Be that way!'

    elsif str
      'Whatever.'
    end

  end
end
