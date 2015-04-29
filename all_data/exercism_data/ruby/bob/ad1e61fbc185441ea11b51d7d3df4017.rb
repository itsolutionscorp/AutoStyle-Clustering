# added these tests to bob_test.rb to make sure that:
class Bob
  def hey(statement)
    # adds downcase chars to statement if it does not contain 
    # any characters a-Z
    #this is necessary to catch the case of only numbers and special
    #characters and white space
    #keeps the last character, in case it is a ?
    unless statement =~ /[a-zA-Z]/ 
      statement = "nothing string" + statement 
    end

    case 
    when statement == statement.upcase
      #when it is all caps
      "Woah, chill out!"
		when statement.end_with?("?")
      #when it ends in a ?
      "Sure."
    when statement =~ /nothing string[\s\t\n]*\z/
      #when it is blank
      "Fine. Be that way!"
    else
      #everything else
      "Whatever."
    end
  end
end
