class Phrase < Struct.new(:phrase)
  def word_count
    normalized_tokens.reduce({}) do |memo, token|
      memo[token] = (memo[token] || 0) + 1
      memo
    end
  end

  def tokens
    phrase.scan(/\w+\b/)
  end

  def normalized_tokens
    tokens.map(&:downcase)
  end
end
