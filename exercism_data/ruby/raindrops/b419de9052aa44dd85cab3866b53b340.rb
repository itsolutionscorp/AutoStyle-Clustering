require 'prime'

# Human-raindrop interface
class Raindrops
  ONOMATOPOEIC_PHONEMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  class << self
    # Convert a raindrop morpheme into an onomatopoeic morpheme.
    #
    # @param morpheme [#prime_division]
    # @return [String]
    def convert(morpheme)
      phonemes = raindrop_split(morpheme).map(&method(:onomatopoeize)).compact

      onomatopoeic_join(phonemes) || morpheme.to_s
    end

    private

    # Join onomatopoeic phonemes into an onomatopoeic morpheme, if possible.
    #
    # @param phonemes [Array]
    # @returm [String, nil]
    def onomatopoeic_join(phonemes)
      phonemes.join unless phonemes.empty?
    end

    # Convert a raindrop phoneme into an onomatopoeic phoneme, if possible.
    #
    # @param phoneme [Integer]
    # @return [String, nil]
    def onomatopoeize(phoneme)
      ONOMATOPOEIC_PHONEMES[phoneme]
    end

    # Split a raindrop morpheme into raindrop phonemes.
    #
    # @param morpheme [#prime_division]
    # @return [Array<Integer>]
    def raindrop_split(morpheme)
      morpheme.prime_division.map(&:first)
    end
  end
end
