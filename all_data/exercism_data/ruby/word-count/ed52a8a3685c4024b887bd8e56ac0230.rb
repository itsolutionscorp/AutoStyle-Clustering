class Phrase

  def initialize(input)
    @sentence = input
  end

  def word_count
    input_without_punctuation.split.each_with_object({}) do |word,tally| 
      tally[word.downcase] == nil ? tally[word.downcase] = 1 : tally[word.downcase] += 1  
    end
  end

  private

  def input_without_punctuation
    @sentence.gsub(/[^a-zA-Z1-9\s]/, " ")
  end

end
