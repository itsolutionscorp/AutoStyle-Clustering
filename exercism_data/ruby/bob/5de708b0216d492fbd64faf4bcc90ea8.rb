class Bob
  def hey said_words
    words = said_words.gsub(' ', '')
    words = words.gsub(',', '')
    words = words.gsub(/\n/, '')
    if words != '' && words == words.upcase && words =~ /[A-Z]/ 
      'Woah, chill out!'
    elsif words =~ /\?$/
      'Sure.'
    elsif words == '' 
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
