class Phrase
  def initialize words
    tokenizer = Tokenizer.new(words)
    @frequencies = Frequencies.new tokenizer
  end

  def word_count
    @frequencies.frequencies
  end
end

class Tokenizer
  def initialize words
    @words = words
  end

  def clean 
    alpha_numeric_only.downcase.split
  end

  def alpha_numeric_only 
    @words.gsub(/[^a-z0-9]/i, ' ')
  end
end

class Frequencies
  def initialize tokenizer
    @tokenizer = tokenizer
  end

  def frequencies
    tokens = @tokenizer.clean
    tokens.group_by {|e| e }.each_with_object({}) do |(word, occurs), result|
      result[word] = occurs.length
    end
  end
end
