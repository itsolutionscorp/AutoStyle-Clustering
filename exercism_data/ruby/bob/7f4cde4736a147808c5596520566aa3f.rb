
class Bob
  private
  PATTERN_RESPONSE = {
    /\A (?=.*?[A-Z]) [^a-z]* [^.] \z/x => 'Woah, chill out!',
    /\A .* \? \z/x                     => 'Sure.',
    /\A \s* \z/x                       => 'Fine. Be that way!',
    //                                 => 'Whatever.'
    }

  public
  def hey msg
    PATTERN_RESPONSE.each_pair do |regex, response|
      return response if msg =~ regex
    end
  end
end
