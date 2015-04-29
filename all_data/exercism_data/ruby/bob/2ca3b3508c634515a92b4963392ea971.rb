class Bob
  
  def hey(input)
    # remove newlines from consideration
    input.delete!("\n")
    # check if anything was said
    return 'Fine. Be that way!' if (input.strip == '')
    # check if shouting.  Shouting is more important than questions.  You have to be shouting a character BTW
    return 'Woah, chill out!' if (input.upcase == input) && (input =~ /[a-zA-Z]/)
    # Only question marks at the end of the string count
    return 'Sure.' if ( input =~ /\?$/ )
    # And the default
    return 'Whatever.' 

  end

end
