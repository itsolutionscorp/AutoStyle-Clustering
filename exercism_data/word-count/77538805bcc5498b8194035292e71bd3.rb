# Pretty boring solution.  I'd prefer to avoid regular expressions
# but couldn't find a way around them.
class Phrase
  def initialize(input)
    @input = input || ""
  end

  def word_count
    split_on_words_and_punctuation(@input) \
      .map { |token| normalize_token(token) } \
      .select { |normalized_token| !normalized_token.empty? } \
      .inject(Hash.new(0)) do |result, normalized_token|
        result[normalized_token] += 1
        result
    end
  end

private
  def normalize_token(token)
    token.downcase.gsub(/[^[[:word:]]]/, "")
  end

  def split_on_words_and_punctuation(input)
    input.split(/\W+/)
  end
end
