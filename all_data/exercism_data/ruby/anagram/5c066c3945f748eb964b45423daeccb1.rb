class Anagram
  attr_reader :body

  def initialize(body)
    @body = body.downcase
  end

  def match(words)
    words.select{|w| anagram?(w)}
  end

  def anagram?(comparison)
    comparison = comparison.downcase
    return false if comparison == body
    comparison_matches?(comparison) 
  end

  private

  def comparison_matches?(comparison)
    comparison_chars = comparison.chars.sort
    body_chars = body.chars.sort
    comparison_chars == body_chars
  end
end
