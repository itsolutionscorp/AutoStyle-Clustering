class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    Hash[@input.scan(/[[:word:]]+/)
               .group_by { |word| word.downcase }
               .map { |word, frequency| [word, frequency.count] }]
  end
end
