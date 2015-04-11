class Bob
  
  def hey message

    # print "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    # print "\n", message

    abbreviations = !!(message =~ /\b([A-Z]{2,})\b/)
    all_caps = message.upcase.eql? message
    bang = message.include? '!'
    empty = message.strip.empty?
    multiline = message.include? "\n"
    question = !!(message =~ /\?/)

    # print "\n...abbreviations:", abbreviations
    # print "\n...all_caps:", all_caps
    # print "\n...bang:", bang
    # print "\n...empty:", empty
    # print "\n...multiline:", multiline
    # print "\n...question:", question

    return 'Fine. Be that way!' if empty
    return 'Whoa, chill out!' if all_caps or (bang and not question)
    return 'Sure.' if question
    return 'Whatever.' if abbreviations or multiline or bang
    'Whatever.'
  end

end
