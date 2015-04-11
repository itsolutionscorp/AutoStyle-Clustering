module Linguistics
  class Sentence

    attr_reader :purpose_patterns

    def initialize(language)
      @purpose_patterns = language[:purpose_patterns]
    end

    def classification_by_purpose(statement)
      @purpose_patterns.reduce([]){|matches, (purpose, rule)|
        matches.push(purpose) if rule.call(statement)
        matches
      } 
    end
  end
end

# ok this is very english-centric 
class TeenageBrain

  attr_reader :sentence_procesor
  
  def initialize(language_processor = Linguistics)
    purpose_patterns = {
      interrogative: ->(s) { s.end_with? "?" },
      imperative:    ->(s) { s.upcase == s },
    }
    @sentence_processor = language_processor::Sentence.new({purpose_patterns: purpose_patterns})
  end
  
  def classify_communication(signal)
    signal = signal || ""
    classifications = @sentence_processor.classification_by_purpose(signal)
    classifications.push(:non_verbal) if signal.nil? || signal.strip.empty?
    classifications
  end
end

class Bob

  attr_reader :brain
  def initialize(brain = TeenageBrain)
   @brain = brain.new
  end

  def hey(message)

    message_classifications = brain.classify_communication(message)

    return 'Fine. Be that way!' if message_classifications.include?(:non_verbal)
    return 'Woah, chill out!'   if message_classifications.include?(:imperative) 
    return 'Sure.'              if message_classifications.include?(:interrogative)

    'Whatever.'
	end
end
