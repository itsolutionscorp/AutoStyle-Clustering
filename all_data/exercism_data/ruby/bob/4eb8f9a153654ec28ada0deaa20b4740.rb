class Bob

  # Bob's very limited social vocabulary
  PHRASEBOOK = { default: "Whatever.", 
      silence: "Fine. Be that way!", 
      questioning: "Sure.", 
      shouted: "Woah, chill out!" }
  

  def hey(phrase)  
    Bob::PHRASEBOOK[SentimentAnalyzer.analyze(phrase)]
  end

end


# Bob is neither a linguist nor particularly interested in the minutiae of 
# speech analysis.  Therefore, we abstract such trivia out of Bob and
# into its own proper class....
class SentimentAnalyzer

  def self.analyze(phrase)
    return :silence if silence(phrase)

    #List of meaningful sentiment classes.
    #Important that these are defined in *ascending* order of importance/interest,
    #as they are evaluated from first to last
    sentiments = ["default","questioning","shouted"]

    sentiments.reduce(sentiments.first) do |curr_sentiment, this_sentiment|  
        (self.send(this_sentiment.to_sym,phrase) == true) ? this_sentiment.to_sym : curr_sentiment.to_sym
    end
  end

protected
  # Simple one-liner sentiment analysis functions...
  def self.default(phrase)
    true
  end

  def self.shouted(phrase)
    # Shouting: defined as the absence of lower-case characters.
    /[[:lower:]]/.match(phrase) == nil
  end

  def self.silence(phrase)
   (phrase == nil) || (phrase == '')
  end

  def self.questioning(phrase)
    # Question: defined as a single question mark at the end of a supplied phrase.
    /\?\Z/.match(phrase) != nil
  end

end
