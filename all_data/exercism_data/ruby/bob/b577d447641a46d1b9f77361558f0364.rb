# Bob is in the news!
#
# http://www.theonion.com/video/braindead-teen-only-capable-of-rolling-eyes-and-te,27225/
#
class Bob
  attr_reader :input

  # Given a string of text, Bob will say something bothersome and piss you off.
  # As a side benefit, he should pass the provided test suite.
  #
  def hey(input)
    @input = BobInputString.new(input.to_s)

    select_response
  end

  private
  # vague priority ordering inferred from test suite. this will grow
  # unwieldy if we get many more challenges.
  #
  def select_response
    response ||= chill! if input.shouting?
    
    # these are effectively redundant *today* but a slightly altered test suite
    # might make us glad to have them explicitly called out.
    response ||= chill! if input.shouting? && input.questioning?
    response ||= chill! if input.shouting? && input.numbers?
    response ||= chill! if input.shouting? && input.gibberish?

    response ||= "Sure."          if input.questioning?
    response ||= default_response if input.forceful?

    # overrides
    response = default_response         if input.acronyms?
    response = 'Fine. Be that way.' unless input.letters?

    response || default_response
  end
  
  # ===============
  # Stock responses
  # ===============

  def default_response
    'Whatever.'
  end

  def chill!
    'Woah, chill out!'
  end
end

# Felt awkward about encoding all of Bob's string processing directly inside Bob,
# so here's a helper class that lets me ask questions of the input itself.
#
class BobInputString < String
  attr_reader :words

  def initialize(str)
    super str.to_s
    @words = split
  end

  # =========
  # Accessors
  # =========

  def special_chars
    @special_chars_memo ||= chars.select{ |char| char =~ /[^0-9a-zA-Z]/ }
  end

  # =========
  # Detectors
  # =========
  
  def gibberish?
    gibberish_threshold = 10.0

    special_chars.count > (length / gibberish_threshold)
  end

  def questioning?
    include? '?'
  end

  def forceful?
    include? '!'
  end

  def acronyms?
    words.select{ |w| w.upcase == w }.any? && words.select{ |w| w.upcase != w }.any?
  end

  def shouting?
    words.select{ |w| w.upcase == w }.any?
  end

  def numbers?
    !!(self =~ /[0-9]/)
  end

  def letters?
    !!(self =~ /[a-zA-Z]/)
  end
end
