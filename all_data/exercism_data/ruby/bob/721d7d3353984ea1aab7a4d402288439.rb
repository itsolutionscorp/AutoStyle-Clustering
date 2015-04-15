class Bob

  def hey(noise)
    classify(noise).respond
  end

  def classify(noise)
    PRIORITIZED_NOISES.select { |noise_type| noise_type.defines?(noise) }.first
  end

  class Noise
    def self.defines?(noise)
      true
    end

    def self.respond
      'Whatever.'
    end
  end

  class Question
    def self.defines?(noise)
      noise.end_with?('?')
    end

    def self.respond
      'Sure.'
    end
  end
  
  class Shouting
    def self.defines?(noise)
      noise =~ /[A-Z]+/ && noise == noise.upcase
    end

    def self.respond
      'Woah, chill out!'
    end
  end

  class Silence
    def self.defines?(noise)
      noise.delete(' ').empty?
    end

    def self.respond
      'Fine. Be that way!'
    end
  end
  
  PRIORITIZED_NOISES = [Shouting, Question, Silence, Noise]
end
