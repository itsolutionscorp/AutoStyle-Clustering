class Phrase
  def initialize(string)
    @words = string.downcase.scan(/\w+/)
  end

  def word_count
    @words.inject({}) do |results, word|
      results.merge!({ word => 1 }) { |_, old, _| old + 1 }
    end
  end
end
