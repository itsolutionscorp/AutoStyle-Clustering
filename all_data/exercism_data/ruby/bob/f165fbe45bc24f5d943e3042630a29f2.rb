class Bob
  def hey said
    if said =~ /[[:upper:]]/ and said =~ /\A([[:upper:]]|[[:blank:]]|[[:punct:]]|[[:digit:]])+\Z/
      'Woah, chill out!'
    elsif said =~ /\?\Z/
      'Sure.'
    elsif said =~ /\A[[:blank:]]*\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
