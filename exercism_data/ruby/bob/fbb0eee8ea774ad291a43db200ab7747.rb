# Bob is a lackadaisical teenager. He only responds to public method `hey`
# and pretty much always responds with a String that's pithy and unsatisfying.
class Bob
  # Take a String, parse it, and respond with the response a teenager would
  # make. We have to turn the String into an IntrospectivePhrase so that we
  # can ask it whether it's a question or a shout. This was done to avoid
  # monkey-patching String.
  # TODO: Implement l33t speak
  def hey(phrase)
    phrase = IntrospectivePhrase.new(phrase)

    if phrase.question?
      return "Sure."
    elsif phrase.silent?
      return "Fine. Be that way."
    elsif phrase.leet?
      return phrase.to_leet
    elsif phrase.shouting?
      return "Woah, chill out!"
    else
      return "Whatever."
    end
  end
end

# IntrospectivePhrase is designed to act like a String but allow extra
# navel gazing introspection. This was done to avoid monkey-patching
# String while still being able to ask said String about its shape.
class IntrospectivePhrase < String
  # Ensure that there is no trailing space after the message. Mostly this
  # is for `question?` but I want to extend `shouting?` to do similar
  # things.
  def initialize(str)
    super(str.strip)
  end

  # Determine whether the phrase is a question.
  def question?
    self.end_with?('?')
  end

  # Determine whether the phrase is a shouty or not.
  def shouting?
    self.upcase == self
  end

  # Determine whether the phrase is emptyish or not.
  def silent?
    self.empty?
  end

  # Determine whether the phrase is asking for a l33t response.
  def leet?
    self.start_with?("Bro,")
  end

  # Transform the phrase into l33t speak
  def to_leet
    # Make sure to strip off the Bro from the beginning
    LeetString.new(self.gsub('Bro,', '').strip).to_s
  end
end

# LeetStrings are Strings that are capable of being transformed into l33t
# speak.
class LeetString < String
  # Create a string representation of this object. This caches the result
  # of building the leet version of the string.
  def to_s
    # to_s is a common function on all objects. Normally, if you don't provide
    # this function we will instead use Object.to_s which uses the objects
    # location in memory and class name. "#<Object:0x007fda6393a630>"
    # to_s is called any time you `puts` or `inspect` an object.

    # Cache this building process using a "guarded assignment". This will
    # ensure that the leet version only gets built once for each object.
    return @leet ||= build_leet_string
  end

  # Build the leet version of a string.
  def build_leet_string!
    # Force the string to be lowercased
    self.downcase!
    # Change the vowels to numbers
    self.gsub!(/[aeio]/, "a" => 4, "e" => 3, "i" => 1, "o" => 0)
    # Alternate case for consonants
    self.alternate_case!

    return self
  end

  # Build the leet version of a string without using self-modifying methods.
  def build_leet_string
    # This form works, but the style is a little gross because the periods
    # are easy to screw up, the indenting is non-obvious, and while it's not
    # explicitly banned in bbatsov's style guide, he does say that
    # "multiline chaining is always ugly".
    return self.dup.build_leet_string!
  end

  # Alternate case between up/downcase for any consecutive sequence of
  # consonants or the letters u or y. This modifies self.
  # Example:
  # "hello".alternate_case! #=> "helLo"
  # "bbb4bbb".alternate_case #=> "bBb4bBb"
  # "BBB".alternate_case #=> "BbB"
  # @see alternate_case
  def alternate_case!
    # Since this is descending from string, we can define new methods here
    # and use them like we would gsub! or downcase!. I've chosen to use a
    # self-modifying form to match the above style.

    # Storage variables
    ret = "" # This time use a normal String
    previous_char = ""

    # Go through each character and upcase it if the previous character
    # was a lower-case consonant
    self.each_char do |char|
      if previous_char =~ /[b-df-hj-np-z]/
        char.upcase!
      end
      ret << char
      previous_char = char
    end

    # Use a destructive method already in String to replace self. We can't
    # just do `self = ret` because of a SyntaxError.
    return self.replace(ret)
  end

  # Alternate case between up/downcase for any consecutive sequence of
  # consonants or the letters u or y.
  # @see alternate_case!
  def alternate_case
    return self.dup.alternate_case!
  end
end
