class Bob
  def hey ( text='' )
    if ( text =~ /^[\s]+$/ ) or text == ''
      return 'Fine. Be that way!'
    elsif ( text =~ /^[A-Z\W\d]+$/ )
      return 'Woah, chill out!'
    elsif ( text =~ /\?$/ )
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
