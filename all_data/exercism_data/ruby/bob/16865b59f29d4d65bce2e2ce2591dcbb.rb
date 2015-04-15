# added these tests to bob_test.rb to make sure that:

=begin
  def test_only_ending_in_silence
    assert_equal 'Whatever.', teenager.hey('1, 2, 3, sing with...    ')
  end

  def test_ending_in_silence_only_numbers
    assert_equal 'Whatever.', teenager.hey('1, 2, 3,    ')
  end
=end

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

    case statement
    when statement.upcase
      #when it is all caps
      "Woah, chill out!"
    when /\?\z/
      #when it ends in a ?
      "Sure."
    when /nothing string[\s\t\n]*\z/
      #when it is blank
      "Fine. Be that way!"
    else
      #everything else
      "Whatever."
    end
  end
end
