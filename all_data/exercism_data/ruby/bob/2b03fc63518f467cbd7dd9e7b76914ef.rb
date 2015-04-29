class Bob

  # Bob's very limited social vocabulary
  PHRASEBOOK = { default: "Whatever.", 
      silence: "Fine. Be that way!", 
      questioning: "Sure.", 
      shouted: "Woah, chill out!" }
  
  def hey(phrase)  
    PHRASEBOOK[SentimentAnalyzer.new(phrase).analyze]
  end

end


# Bob is neither a linguist nor particularly interested in the minutiae of 
# speech analysis.  Therefore, we abstract such trivia out of Bob and
# into its own proper class....
class SentimentAnalyzer

  DEFAULT = :default

  def initialize(phrase)
    # Guard against a nil phrase by forcing the supplied param to a string.
    @phrase = String(phrase).strip
  end

  def analyze
    return :silence if silence?

    #List of meaningful sentiment classes.
    #Important that these are defined in *decreasing* order of importance/interest,
    #as they are evaluated from highest to lowest precedence.

    sentiments = [:shouted, :questioning]
    sentiments.detect( lambda {DEFAULT} ) { |s| send("#{s}?".to_sym) }
  end

private

  def shouted?
    # Shouting: defined as the absence of lower-case characters.
    /[[:lower:]]/.match(@phrase) == nil
  end

  def silence?
    @phrase == ''
  end

  def questioning?
    # Question: defined as a single question mark at the end of a supplied phrase.
    /\?\Z/.match(@phrase) != nil
  end

end
