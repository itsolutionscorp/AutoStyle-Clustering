# coding: utf-8

class Phrase
  def initialize(str)
    @words = str.gsub(/[^\w\s,|]/,'').split(/\s|,/)
  end

  def word_count
    calculate_word_count unless @word_count
    @word_count
  end

  private
    def calculate_word_count
      @word_count ||= @words.reduce({}) do |hash, word|
        unless word.empty?
          key = word.downcase
          hash[key] = hash[key].to_i + 1
        end

        hash
      end
    end
end
