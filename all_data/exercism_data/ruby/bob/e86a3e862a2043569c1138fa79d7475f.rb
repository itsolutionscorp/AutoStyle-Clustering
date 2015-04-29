# Bob responds differently depending on what kind of (phrase) you say to him.
class Bob
  def hey(phrase)

    # Say nothing and Bob responds "Fine. Be that way!"
    if phrase.strip == ""
      "Fine. Be that way!"

    # Shout at Bob (with ALL CAPS), and he'll tell you "Whoa, chill out!"
    # Lets strip out all the non-alpha chars (and confirm there are alpha chars
    # that remain) and compare them to the upcase version of themselves.
    elsif  phrase.gsub(/[\W0-9]/, '') == phrase.gsub(/[\W0-9]/, '').upcase \
        && phrase.gsub(/[\W0-9]/, '').empty? == false
      "Woah, chill out!"

    # If the last char is a '?', Bob will respond "Sure."
    elsif phrase[-1] == "?"
      "Sure."

    # Anything else you say to Bob will elicit a "Whatever."
    else
      "Whatever."
    end

  end
end
