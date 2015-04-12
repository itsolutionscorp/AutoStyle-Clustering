class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    Hash[@input.downcase
               .scan(/[[:word:]]+/)
               .group_by { |word| word }
               .map { |word, frequency| [word, frequency.count] }]
  end
end
