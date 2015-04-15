class Words
  attr_reader :words

  def initialize(words)
    @words = cleanup(words.split)
  end

  def count
    result = Hash.new(0)
    words.each {|w| result[w.downcase] += 1}
    result
  end

  private
    def cleanup(words)
      words.each {|word| word.gsub!(/\W+/, '')}.reject(&:empty?)
    end
end
