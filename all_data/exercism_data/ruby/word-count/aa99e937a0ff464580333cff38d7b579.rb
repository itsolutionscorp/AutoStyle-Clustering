require 'byebug'
class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    cleansed = @str.downcase
      .gsub(/[^\w\s\n,']/, '')
      .split(/[ ,]/)
      .select { |w| w != '' }

    cleansed.inject(Hash.new { 0 }) do |word_count, word|
      word_count[word] += 1
      word_count
    end
  end
end
