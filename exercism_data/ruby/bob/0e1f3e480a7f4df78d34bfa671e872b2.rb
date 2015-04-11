class Bob

  def hey(sentence)
    sentence.strip!
    return 'Fine. Be that way!' if sentence.empty?
    sentence.gsub!(/[^a-zA-Z!\?]/, '')
    if sentence[-1] == '?'
      return 'Whoa, chill out!' if sentence == sentence.upcase && sentence.length != 1
      return 'Sure.'
    end
    return 'Whoa, chill out!' if sentence == sentence.upcase && !sentence.empty?
    'Whatever.'
  end
end
