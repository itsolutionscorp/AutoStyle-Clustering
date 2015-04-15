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
    @phrase = String(phrase).strip
  end

  def analyze
    prioritized_sentiments.detect( lambda {DEFAULT} ) { |s| send("#{s}?")  }
  end

private

  def prioritized_sentiments
    [:silence, :shouted, :questioning]
  end

  def shouted?
    /[[:lower:]]/.match(@phrase) == nil
  end

  def silence?
    @phrase == ''
  end

  def questioning?
    /\?\Z/.match(@phrase) != nil
  end

end
