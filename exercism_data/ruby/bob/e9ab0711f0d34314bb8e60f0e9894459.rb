class Bob

  RESPONSES = {
    /\A *\z/ => 'Fine. Be that way!',
    /\A[A-Z0-9 \W]+\z/ => 'Woah, chill out!',
    /\?\z/ => 'Sure.',
    /.*/ => 'Whatever.',
  }

  def hey(incoming)
    RESPONSES.detect {|k,v| incoming.to_s =~ k }.last
  end
end
