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
  attr_reader :input, :words

  def initialize(str)
    @input = str.to_s
    @words = input.split
    super input
  end

  # =========
  # Accessors
  # =========

  # Makes me behave like a String
  def to_s
    input
  end

  # Makes me behave like a String
  def to_str
    input
  end

  def special_chars
    @special_chars_memo ||= input.chars.select{ |char| char =~ /[^0-9a-zA-Z]/ }
  end

  # =========
  # Detectors
  # =========
  
  def gibberish?
    gibberish_threshold = 10.0

    special_chars.count > (input.length / gibberish_threshold)
  end

  def questioning?
    input.include? '?'
  end

  def forceful?
    input.include? '!'
  end

  def acronyms?
    words.select{ |w| w.upcase == w }.any? && words.select{ |w| w.upcase != w }.any?
  end

  def shouting?
    input.split.select{ |w| w.upcase == w }.any?
  end

  def numbers?
    !!(input =~ /[0-9]/)
  end

  def letters?
    !!(input =~ /[a-zA-Z]/)
  end
end
