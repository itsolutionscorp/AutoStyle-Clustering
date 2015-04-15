class Bob

  RESPONSES = {
    /^ *$/ => 'Fine. Be that way!',
    /^[A-Z0-9 \W]+$/ => 'Woah, chill out!',
    /\?$/ => 'Sure.',
    /.*/ => 'Whatever',
  }

  def hey(incoming)
    RESPONSES.detect {|k,v| incoming.to_s =~ k }.last
  end
end
