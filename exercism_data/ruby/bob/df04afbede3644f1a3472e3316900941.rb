class Bob

  # man, that multi-line made these uglier and more brittle. sad.
  MATCHERS = {
    /^ *\Z/         => "Fine. Be that way!",  # silence
    /^[^a-z]+!$/    => "Woah, chill out!",    # shouting, no lowercase
    /^[A-Z ]+\??$/  => "Woah, chill out!",    # speaking loudly, still rude.
    /\?\Z/          => "Sure.",               # questions
    /.*/            => "Whatever."            # fallthrough, everything else
  }

  def hey(msg)
    MATCHERS.each do |match,message|
      return message if msg =~ match
    end
  end

end
