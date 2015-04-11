class Bob
  @@response = {
    question:  'Sure.',
    yell:      'Woah, chill out!',
    silence:   'Fine. Be that way!',
    other:     'Whatever.'
   }

  def hey(phrase)
    @@response[parse(phrase)]
  end

  private
  def parse(phrase)
    sentence = phrase.gsub("\n", ' ')
    return :silence if sentence.strip.empty?
    return :yell if sentence.match(/^[^a-z]+!$|^(\s*[A-Z]\s*)+\??$/)
    return :question if sentence.match(/^.+\?$/)
    :other
  end
end
