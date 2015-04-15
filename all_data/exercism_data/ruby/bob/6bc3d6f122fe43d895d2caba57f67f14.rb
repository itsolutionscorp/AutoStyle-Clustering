require 'yaml'
class Bob

  RESPONSES = {
    /^ *$/ => 'Fine. Be that way!',
    /^[A-Z0-9 \W]+$/ => 'Woah, chill out!',
    /\?$/ => 'Sure.',
  }

  def hey(incoming)
    if special = RESPONSES.detect {|k,v| incoming.to_s =~ k }
      return special.last
    end
    'Whatever.'
  end
end
