class Bob

  def hey phrase
    responses[ Phrase.new( phrase ).type ]
  end

  private

    def responses
      {
        default:  'Whatever.',
        shouting: 'Woah, chill out!',
        question: 'Sure.',
        silence:  'Fine. Be that way!'
      }
    end

end

class Phrase

  def initialize string
    @phrase = string
  end

  def type
    return :silence  if silence?
    return :shouting if shouting?
    return :question if question?
    return :default
  end

  def shouting?
    @phrase.upcase == @phrase
  end

  def silence?
    @phrase.nil? || @phrase.strip.empty?
  end

  def question?
    @phrase.end_with? '?'
  end

end
