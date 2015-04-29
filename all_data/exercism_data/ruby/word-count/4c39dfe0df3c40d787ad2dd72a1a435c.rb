Phrase = Struct.new(:phrase) do
  def word_count
    Frequency.of words
  end

  private
  def words
    phrase.downcase.scan(/[[:alnum:]]+/)
  end
end

module Frequency
  def self.of(enum)
    enum.each_with_object(Hash.new(0)) do |el, frequency|
      frequency[el] += 1
    end
  end
end
