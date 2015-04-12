class Words

  attr_reader :words

  def initialize(input)
    @words = input.downcase.split
  end

  def validated_words
    words.collect{|word| word.delete("^[a-zA-Z0-9]")}.delete_if{|word| word == ""}
  end

  def count
    validated_words.inject(Hash.new(0)) {|word, count| word[count] += 1; word}
  end
end
