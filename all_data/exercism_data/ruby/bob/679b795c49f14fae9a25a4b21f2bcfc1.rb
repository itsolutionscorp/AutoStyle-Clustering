class Bob
  def hey(input_text)

    # Removes all line breaks and extranneous spaces from the string
    modified_str = input_text.gsub("\n", " ").rstrip

    case
    when modified_str == ''
      'Fine. Be that way!'
    when modified_str == modified_str.upcase && modified_str =~ /[A-Z]/
      'Woah, chill out!'
    when modified_str =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
