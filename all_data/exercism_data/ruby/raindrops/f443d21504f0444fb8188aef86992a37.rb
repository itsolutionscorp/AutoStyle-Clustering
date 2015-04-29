# Human-raindrop interface
class Raindrops
  PHONEMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  class << self
    # Convert a raindrop into a human-friendly format.
    #
    # @param raindrop [Numeric]
    # @return [String]
    def convert(raindrop)
      onomatopoeic(raindrop) || raindrop.to_s
    end

    private

    # Represent a raindrop as an onomatopoeic morpheme, if possible.
    #
    # @param raindrop [Numeric]
    # @return [String, nil]
    def onomatopoeic(raindrop)
      PHONEMES.reduce nil do |morpheme, (factor, phoneme)|
        if raindrop.modulo(factor).zero?
          [morpheme, phoneme].join
        else
          morpheme
        end
      end
    end
  end
end
