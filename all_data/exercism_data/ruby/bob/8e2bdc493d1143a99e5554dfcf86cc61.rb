class Bob
  def hey what
    what = what.gsub(/\d/, '')
    return "Fine. Be that way!" if what.gsub(' ', '') == ''

    if what =~ /\w/ && what == what.upcase 
      'Woah, chill out!'
    else 
      if what[-1]['?']
        "Sure."
      else
        "Whatever."
      end
    end
  end
end
