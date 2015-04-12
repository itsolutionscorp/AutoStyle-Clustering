class Phrase
  def initialize(p)
    @words = p
      .downcase
      .gsub(/[.!&@$%^:]/, '')
      .gsub(/,/, ' ')
      .gsub(/\s{2}/, ' ')
      .split(' ')
  end

  def word_count
    count = Hash.new

    @words.each do |w|
      count[w] = !count[w].nil? ? count[w] + 1 : 1
    end

    return count
  end
end
