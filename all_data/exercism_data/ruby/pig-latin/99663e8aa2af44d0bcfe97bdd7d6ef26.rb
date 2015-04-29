class PigLatin
  def self.translate(words)
    new(words).translate
  end

  def initialize(words)
    @words = words
  end

  def translate
    words.split(' ').map do |word|
      word.gsub(/([xy][^aeiou]\w*|[^aeiou]*q?u?(?=[aeiou]))(\w*)/, '\2\1ay')
    end.join(' ')
  end

  private

  attr_reader :words
end
