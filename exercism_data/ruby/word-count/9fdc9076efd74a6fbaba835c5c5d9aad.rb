class Phrase

  PUNCTUATIONS = [":", "!", "&", "@", "^", '"', '$', '%']

  def initialize(word)
    @word = word.downcase
    remove_punctuations
  end

  def word_count
    word_count_hash = Hash.new(0)
    @word.split(/[ ,]/).
      reject { |s| s == "" }.
      each { |w| word_count_hash[w] += 1 }
    word_count_hash
  end

  private

    def remove_punctuations
      PUNCTUATIONS.each { |p| @word.gsub!(p, '') }
    end
end
