class Bob
  REGEX = { empty:    /^[^\w]*$/,
            forceful: /^[A-Z\s\d\W]*$/,
            question: /[?]+$/ }

  RESPONSE = { empty:    'Fine. Be that way!',
               forceful: 'Woah, chill out!',
               question: 'Sure.',
               general:  'Whatever.'}

  def hey(msg)
    return RESPONSE[:empty]    if msg =~ REGEX[:empty] || msg.nil?
    return RESPONSE[:forceful] if msg =~ REGEX[:forceful]
    return RESPONSE[:question] if msg =~ REGEX[:question]

    RESPONSE[:general]
  end
end
