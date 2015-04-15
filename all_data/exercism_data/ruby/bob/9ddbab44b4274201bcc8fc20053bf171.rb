class Bob
  # Talk to Bob, tell him what you'd like
  #
  # @param [String] What you want to say to Bob
  # @return [String] What Bob tells you...
  def hey (input)
    # Remove whitespace
    input.strip!

    # Empty comments
    if input == ''
      return 'Fine. Be that way!'
    elsif input === input.upcase && input.match(/[[:alpha:]]+/)
      return 'Woah, chill out!'
    elsif input.end_with? '?'
      return 'Sure.'
    end

    # Catch-all
    'Whatever.'
  end
end
